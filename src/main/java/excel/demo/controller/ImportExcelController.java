package excel.demo.controller;

import excel.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/import-excel")
public class ImportExcelController {

  @Autowired
  CustomerService customerService;

  @PostMapping("/import")
  public void importExcelDataToDB(@RequestParam("file") MultipartFile file) {

    customerService.readFile(file);

  }

}
