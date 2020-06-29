package excel.demo.service;

import excel.demo.dto.CustomerDto;
import excel.demo.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {

    void readFile(MultipartFile file);

    List<Customer> getAll();

    Customer addCustomer(CustomerDto customerDto);

    Customer getDetail(Long id);
}
