package ru.manasyan.advertising.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.manasyan.advertising.data.entities.Identifiable;

import java.util.List;

@NoRepositoryBean
public interface SearchableRepository<E extends Identifiable, ID> extends CrudRepository<E, ID> {
    List<E> search(String template);
}
