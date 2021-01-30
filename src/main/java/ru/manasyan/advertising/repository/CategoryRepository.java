package ru.manasyan.advertising.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.manasyan.advertising.data.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends SearchableRepository<Category, Integer> {
    @Override
    @Query("select c from Category c " +
            "where lower(c.name) like :#{#template} " +
            "and c.isDeleted = false")
    List<Category> search(String template);

    boolean existsByName(String name);

    boolean existsByRequestName(String requestName);
}
