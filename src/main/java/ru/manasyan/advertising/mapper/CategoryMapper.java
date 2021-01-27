package ru.manasyan.advertising.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.manasyan.advertising.data.dto.CategoryDto;
import ru.manasyan.advertising.data.entities.Category;

@RequiredArgsConstructor
@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {
    @Override
    public Category toEntity(CategoryDto dto) {
        return new Category(
                dto.getName(),
                dto.getRequestName()
        );
    }
}
