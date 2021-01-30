package ru.manasyan.advertising.service;

import ru.manasyan.advertising.data.dto.SearchInfo;
import ru.manasyan.advertising.data.entities.Identifiable;

public interface CrudService<E extends Identifiable> {
    void add(E entity);

    void delete(int entityId);

    void update(E entity);

    E getById(int id);

    Iterable<SearchInfo> search(String template);
}
