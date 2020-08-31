package excel.demo.service.impl;

import excel.demo.dto.CategoriesDto;
import excel.demo.entity.Category;
import excel.demo.repository.CategoryRepository;
import excel.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoriesDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoriesDto> categoriesDtos = new ArrayList<>();
        categories.stream().map(category -> {
            CategoriesDto categoriesDto = CategoriesDto.builder().name(category.getName()).build();
            return categoriesDtos.add(categoriesDto);
        }).collect(Collectors.toList());
        return categoriesDtos;
    }
}
