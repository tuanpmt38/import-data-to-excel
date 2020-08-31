package excel.demo.controller;

import excel.demo.dto.CategoriesDto;
import excel.demo.entity.Category;
import excel.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by TuanPM on 2020-06-28.
 **/
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoriesDto>> getAllProduct() {

        List<CategoriesDto> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }


}
