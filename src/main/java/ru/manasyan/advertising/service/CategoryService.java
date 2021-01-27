package ru.manasyan.advertising.service;

import org.springframework.stereotype.Service;
import ru.manasyan.advertising.data.dto.SearchInfo;
import ru.manasyan.advertising.data.entities.Category;
import ru.manasyan.advertising.repository.CategoryRepository;

@Service
public class CategoryService extends AbstractCrudService<Category> {
    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    @Override
    protected SearchInfo toSearchInfo(Category entity) {
        return new SearchInfo(
                entity.getId(),
                entity.getName()
        );
    }
}
