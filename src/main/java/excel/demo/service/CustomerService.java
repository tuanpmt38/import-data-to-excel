package excel.demo.service;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public interface CustomerService {

  void writeExcel(HttpServletResponse response) throws IOException;
}
