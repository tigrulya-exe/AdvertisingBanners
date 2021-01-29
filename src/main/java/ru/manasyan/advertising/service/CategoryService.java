package ru.manasyan.advertising.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manasyan.advertising.data.dto.SearchInfo;
import ru.manasyan.advertising.data.entities.Banner;
import ru.manasyan.advertising.data.entities.Category;
import ru.manasyan.advertising.repository.CategoryRepository;

import java.util.Set;

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

    @Transactional
    @Override
    public void delete(int id) {
        Set<Banner> banners = getById(id).getBanners();
        if (!banners.isEmpty()) {
            // TODO call transactional from transactional
            throw new IllegalStateException(
                    "Can't delete category, contains: " + banners
            );
        }
        super.delete(id);
    }
}
