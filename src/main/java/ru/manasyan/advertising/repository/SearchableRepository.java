package ru.manasyan.advertising.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.manasyan.advertising.data.entities.Identifiable;

import java.util.List;

@NoRepositoryBean
public interface SearchableRepository<E extends Identifiable, ID> extends JpaRepository<E, ID> {
    List<E> search(String template);
}
