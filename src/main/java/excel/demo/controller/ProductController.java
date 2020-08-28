package excel.demo.controller;

import excel.demo.dto.ProductDto;
import excel.demo.dto.ProductResponseDto;
import excel.demo.dto.QueryProductDto;
import excel.demo.entity.Product;
import excel.demo.reponse.ResponseWithPage;
import excel.demo.service.ProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue = "15"),
            })
    @GetMapping("pages")
    public ResponseEntity<ResponseWithPage<ProductDto>> getAll(
            @ModelAttribute QueryProductDto data, @ApiIgnore Pageable pageable) {
        Page<ProductDto> page = productService.findAllProductByData(data, pageable);
        return ResponseEntity
                .ok(ResponseWithPage.<ProductDto>builder().data(page.getContent())
                        .pageIndex(page.getNumber())
                        .totalPage(page.getTotalPages()).totalElement(page.getTotalElements()).build());
    }
    @GetMapping("data")
    public Map<Long, Product> mapProduct (){
        return productService.getMapData();
    }
}
