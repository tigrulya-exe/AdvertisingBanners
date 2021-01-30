package ru.manasyan.advertising.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manasyan.advertising.data.dto.SearchInfo;
import ru.manasyan.advertising.data.entities.Banner;
import ru.manasyan.advertising.data.entities.Category;
import ru.manasyan.advertising.data.entities.Identifiable;
import ru.manasyan.advertising.exceptions.AlreadyExistsException;
import ru.manasyan.advertising.exceptions.CategoryDeleteException;
import ru.manasyan.advertising.repository.CategoryRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService extends AbstractCrudService<Category> {
    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    @Override
    protected void validate(Category entity) {
        CategoryRepository repository = (CategoryRepository) getRepository();
        if (repository.existsByName(entity.getName())) {
            throw new AlreadyExistsException("name");
        }

        if (repository.existsByRequestName(entity.getRequestName())) {
            throw new AlreadyExistsException("requestName");
        }
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
            throw new CategoryDeleteException(
                banners.stream()
                        .map(Identifiable::getId)
                        .collect(Collectors.toSet())
            );
        }
        super.delete(id);
    }
}
