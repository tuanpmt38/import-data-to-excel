package excel.demo.service.impl;

import excel.demo.entity.Customer;
import excel.demo.repository.CustomerRepository;
import excel.demo.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    private static final int COLUMN_INDEX_ID = 0;
    private static final int COLUMN_INDEX_NAME = 1;
    private static final int COLUMN_INDEX_ADDRESS = 2;
    private static final int COLUMN_INDEX_AGE = 3;
    private static final int COLUMN_INDEX_BIRTH_DAY = 4;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void readFile(MultipartFile file) {

        //read file
        List<Customer> customers = null;
        try {
            customers = readExcel(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!CollectionUtils.isEmpty(customers)) {
            customers.forEach(customer -> {
                customerRepository.save(customer);
            });
        }
    }

    private List<Customer> readExcel(MultipartFile excelFilePath) throws IOException {

        List<Customer> listBooks = new ArrayList<>();
        // Get workbook, workbook đại diện cho 1 file excel
        Workbook workbook = getWorkbook(excelFilePath);
        // Get sheet, đại diện cho một bảng tính
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows, row đại diện cho một hàng trong sheet
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            // Get all cells, cell đại diện cho một ô trong row
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for customer object
            Customer customer = new Customer();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for customer object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_ID:
                        customer.setId(new BigDecimal((double) cellValue).longValue());
                        break;
                    case COLUMN_INDEX_NAME:
                        customer.setName((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_ADDRESS:
                        customer.setAddress((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_AGE:
                        customer.setAge(new BigDecimal((double) cellValue).longValue());
                        break;
                    case COLUMN_INDEX_BIRTH_DAY:
                        customer.setBirthday((Date) getCellValue(cell));
                        break;
                    default:
                        break;
                }
            }
            listBooks.add(customer);
        }
        workbook.close();
        return listBooks;
    }

    // Get Workbook
    private static Workbook getWorkbook(MultipartFile excelFilePath) throws IOException {

        Workbook workbook;
        if (Objects.requireNonNull(excelFilePath.getOriginalFilename()).endsWith("xlsx")) {

            //XSSF đọc file excel 2007 trở lên
            workbook = new XSSFWorkbook(excelFilePath.getInputStream());
        } else if (excelFilePath.getOriginalFilename().endsWith("xls")) {
            workbook = new HSSFWorkbook(excelFilePath.getInputStream());
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue();
                } else {
                    cellValue = cell.getNumericCellValue();
                }
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }


    @Override
    public Map<String, Object> export() {

        String template = "classpath:excel_template/student.xls";
        List<Customer> customers = customerRepository.findAll();

        Resource templateResource = resourceLoader.getResource(template);
        if (!templateResource.exists()) {
            return null;
        }

        InputStream inputStream = null;
        try {
            inputStream = resourceLoader.getResource(template).getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Context context = new Context();
        context.putVar("customer", customers);
        try {
            JxlsHelper.getInstance().processTemplate(inputStream, outputStream, context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to determine file's content type
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String fileName = "Danh_sach_san_pham_" + simpleDateFormat.format(new Date()) + ".xlsx";
        Map<String, Object> result = new HashMap<>();
        result.put("fileName", fileName);
        result.put("resource", new ByteArrayResource(outputStream.toByteArray()));

        return result;

    }
}
