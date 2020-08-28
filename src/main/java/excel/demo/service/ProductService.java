package excel.demo.service;

import excel.demo.dto.ProductDto;
import excel.demo.dto.ProductResponseDto;
import excel.demo.dto.QueryProductDto;
import excel.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {


    List<ProductResponseDto> getAll();

    Product addProduct(ProductDto productDto);

    Product getDetail(Long id);

    Page<ProductDto> findAllProductByData(QueryProductDto data, Pageable pageable);

    Map<Long, Product> getMapData();

}
