package excel.demo.service;

import excel.demo.dto.CategoriesDto;
import excel.demo.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoriesDto> getAll();

}
