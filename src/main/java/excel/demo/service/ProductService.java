package excel.demo.service;

import excel.demo.dto.ProductDto;
import excel.demo.dto.ProductResponseDto;
import excel.demo.entity.Product;

import java.util.List;

public interface ProductService {


    List<ProductResponseDto> getAll();

    Product addProduct(ProductDto productDto);

    Product getDetail(Long id);
}
