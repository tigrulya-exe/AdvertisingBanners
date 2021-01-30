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
        Category category = new Category(
                dto.getName(),
                dto.getRequestName()
        );

        if (dto.getId() != null) {
            category.setId(dto.getId());
        }
        return category;
    }

    @Override
    public CategoryDto toDto(Category entity) {
        return new CategoryDto(
                entity.getId(),
                entity.getName(),
                entity.getRequestName()
        );
    }
}
