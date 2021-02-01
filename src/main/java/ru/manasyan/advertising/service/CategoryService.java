package ru.manasyan.advertising.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manasyan.advertising.data.dto.SearchInfo;
import ru.manasyan.advertising.data.entities.Banner;
import ru.manasyan.advertising.data.entities.Category;
import ru.manasyan.advertising.data.entities.Identifiable;
import ru.manasyan.advertising.exceptions.AlreadyExistsException;
import ru.manasyan.advertising.exceptions.CategoryRemovalException;
import ru.manasyan.advertising.repository.CategoryRepository;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CategoryService extends AbstractCrudService<Category> {
    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Set<Banner> banners = getById(id).getBanners();
        if (!banners.isEmpty()) {
            throw new CategoryRemovalException(
                    banners.stream()
                            .map(Identifiable::getId)
                            .collect(Collectors.toSet())
            );
        }
        super.delete(id);
    }

    public List<String> getRequestNames() {
        return getRepository().findAll()
                .stream()
                .map(Category::getRequestName)
                .collect(Collectors.toList());
    }

    @Override
    protected SearchInfo toSearchInfo(Category entity) {
        return new SearchInfo(
                entity.getId(),
                entity.getName()
        );
    }

    @Override
    protected CategoryRepository getRepository() {
        return (CategoryRepository) super.getRepository();
    }

    @Override
    protected void validate(Category entity) {
        CategoryRepository repository = getRepository();
        Predicate<Category> predicate = c -> c.getId() != entity.getId();

        repository.findByName(entity.getName())
                .filter(predicate)
                .ifPresent(c -> {
                    throw new AlreadyExistsException(
                            "name", entity.getName()
                    );
                });

        repository.findByName(entity.getRequestName())
                .filter(predicate)
                .ifPresent(c -> {
                    throw new AlreadyExistsException(
                            "requestName", entity.getRequestName()
                    );
                });
    }
}
