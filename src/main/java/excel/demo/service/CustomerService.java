package excel.demo.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CustomerService {

  void readFile(MultipartFile file);

  ByteArrayResource export() throws IOException;
}
