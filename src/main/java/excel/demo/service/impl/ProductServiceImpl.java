package excel.demo.service.impl;

import excel.demo.dto.ProductDto;
import excel.demo.dto.ProductResponseDto;
import excel.demo.entity.Product;
import excel.demo.repository.ProductRepository;
import excel.demo.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
                    BeanUtils.copyProperties(product,productResponseDto,product.getColor());
                    productResponseDto.setColor(Integer.parseInt(product.getColor()));
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

}
