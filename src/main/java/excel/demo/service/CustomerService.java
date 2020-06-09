package excel.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CustomerService {

  void readFile(MultipartFile file);

  Map<String, Object> export();
}
