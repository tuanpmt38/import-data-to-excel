package excel.demo.service.impl;

import excel.demo.dto.ProductDto;
import excel.demo.dto.ProductResponseDto;
import excel.demo.dto.QueryProductDto;
import excel.demo.entity.Product;
import excel.demo.repository.ProductRepository;
import excel.demo.repository.spec.ProductSpec;
import excel.demo.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductResponseDto> getAll() {
        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        products.stream().map(
                product -> {
                    ProductResponseDto productResponseDto = new ProductResponseDto();
                    BeanUtils.copyProperties(product,productResponseDto);
                    productResponseDto.setColor(product.getColor().intValue());
                    return productResponseDtos.add(productResponseDto);
                }
        ).collect(Collectors.toList());
        return productResponseDtos;
    }



    @Override
    public Product addProduct(ProductDto productDto) {

        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        return productRepository.save(product);
    }

    @Override
    public Product getDetail(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public Page<ProductDto> findAllProductByData(QueryProductDto data, Pageable pageable) {

        Page<Product> products = productRepository.findAll(ProductSpec.findAllProducts(data), pageable);
        if (CollectionUtils.isEmpty(products.getContent())){
            return Page.empty();
        }
        return products.map(
                product -> {
                    return ProductDto.builder()
                            .description(product.getDescription())
                            .image(product.getImage())
                            .price(BigDecimal.valueOf(product.getPrice()))
                            .title(product.getTitle())
                            .size(product.getSize())
                            .build();
                }
        );

    }

    @Override
    public Map<Long, Product> getMapData() {

        List<Product> products = productRepository.findAll();
        return products.stream().collect(Collectors.toMap(Product::getId, o->o));
    }

}
