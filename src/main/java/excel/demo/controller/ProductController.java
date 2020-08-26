package excel.demo.controller;

import excel.demo.dto.ProductDto;
import excel.demo.dto.ProductResponseDto;
import excel.demo.entity.Product;
import excel.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by TuanPM on 2020-06-28.
 **/
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProduct() {

        List<ProductResponseDto> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {

        Product product = productService.addProduct(productDto);
        return ResponseEntity.ok(product);

    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getDetail(@PathVariable("id") Long id) {
        Product product = productService.getDetail(id);
        return ResponseEntity.ok(product);
    }
}
