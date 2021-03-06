package ru.manasyan.advertising.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.manasyan.advertising.data.dto.CategoryDto;
import ru.manasyan.advertising.data.entities.Category;
import ru.manasyan.advertising.mapper.Mapper;
import ru.manasyan.advertising.service.CategoryService;
import ru.manasyan.advertising.service.CrudService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractCrudController<Category, CategoryDto> {
    public CategoryController(
            CrudService<Category> service,
            Mapper<Category, CategoryDto> mapper) {
        super(service, mapper);
    }

    @GetMapping("/req-names")
    public List<String> getRequestNames(){
        CategoryService service  = (CategoryService) getService();
        return service.getRequestNames();
    }
}
