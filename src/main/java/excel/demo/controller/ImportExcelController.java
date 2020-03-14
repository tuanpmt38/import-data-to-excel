package excel.demo.controller;

import excel.demo.service.CustomerService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/export-excel")
public class ImportExcelController {

  @Autowired
  CustomerService customerService;

  @PostMapping("/export")
  public void importExcelDataToDB(HttpServletResponse httpServletResponse) {

    try {
      customerService.writeExcel(httpServletResponse);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
