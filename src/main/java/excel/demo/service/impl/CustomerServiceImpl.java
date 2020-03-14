package excel.demo.service.impl;

import excel.demo.entity.Customer;
import excel.demo.repository.CustomerRepository;
import excel.demo.service.CustomerService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  private static final int COLUMN_INDEX_ID = 0;
  private static final int COLUMN_INDEX_NAME = 1;
  private static final int COLUMN_INDEX_ADDRESS = 2;
  private static final int COLUMN_INDEX_AGE = 3;
  private static final int COLUMN_INDEX_BIRTH_DAY = 4;

  private static CellStyle cellStyleFormatNumber = null;

  @Override
  public void writeExcel(HttpServletResponse response) throws IOException {

    // get list customer from database

    List<Customer> customers = getCustomer();

    // Create a Workbook
    Workbook workbook = new XSSFWorkbook();
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);
    headerFont.setFontHeightInPoints((short) 14);
    headerFont.setColor(IndexedColors.BLACK.getIndex());

    // region create cell styles to format
    /* CreationHelper helps us create instances of various things like DataFormat,
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
    CreationHelper createHelper = workbook.getCreationHelper();

    // Create Cell Style for formatting Date
    CellStyle dateCellStyle = workbook.createCellStyle();
    dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

    CellStyle currency = workbook.createCellStyle();
    DataFormat currencyFormatter = workbook.createDataFormat();
    currency.setDataFormat(currencyFormatter.getFormat("#,##0.0"));

    CellStyle phoneNumberStyle = workbook.createCellStyle();
    DataFormat phoneFormatter = workbook.createDataFormat();
    phoneNumberStyle.setDataFormat(phoneFormatter.getFormat("(###) ###-####"));

    // Create a CellStyle with the font
    CellStyle headerCellStyle = workbook.createCellStyle();
    headerCellStyle.setFont(headerFont);
    headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
    headerCellStyle.setFillPattern(FillPatternType.FINE_DOTS);
    //endregion

    response.setHeader("Content-Disposition", "attachment;filename=\"customer.xls\"");
    Sheet sheet = workbook.createSheet("Customer Data");
    Row header = sheet.createRow(0);
    header.createCell(0).setCellValue(" ID");
    header.createCell(1).setCellValue(" Name");
    header.createCell(2).setCellValue(" Address");
    header.createCell(3).setCellValue(" Age");
    header.createCell(4).setCellValue(" Birthday");

    int rowNum = 1;
    for (Customer customer : customers) {
      Row row = sheet.createRow(rowNum++);
      row.createCell(0).setCellValue(customer.getId());
      row.createCell(1).setCellValue(customer.getName());
      row.createCell(2).setCellValue(customer.getAddress());
      row.createCell(3).setCellValue(customer.getAge());

      // region Column 1: birthday
      Cell birthdayCell = row.createCell(4);
      birthdayCell.setCellValue(customer.getBirthday());
      birthdayCell.setCellStyle(dateCellStyle);
    }
    // Try to determine file's content type
    String pattern = "MM-dd-yyyy-HH-mm-ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String fileName = "Du_lieu_" + simpleDateFormat.format(new Date()) + ".xlsx";
    response
        .setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response
        .setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

    try {
      workbook.write(response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
    // Closing the workbook
    workbook.close();

  }

  private List<Customer> getCustomer() {

    List<Customer> customers = customerRepository.findAll();

    return customers;
  }
}
