package excel.demo.controller;

import excel.demo.dto.CustomerDto;
import excel.demo.entity.Customer;
import excel.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by TuanPM on 2020-06-28.
 **/
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer() {

        List<Customer> customers = customerService.getAll();
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(CustomerDto customerDto) {

        Customer customer = customerService.addCustomer(customerDto);
        return ResponseEntity.ok(customer);

    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getDetail(@PathVariable("id") Long id) {
        Customer customer = customerService.getDetail(id);
        return ResponseEntity.ok(customer);
    }
}
