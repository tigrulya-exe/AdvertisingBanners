package ru.manasyan.advertising.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manasyan.advertising.data.dto.SearchInfo;
import ru.manasyan.advertising.data.entities.Deletable;
import ru.manasyan.advertising.exceptions.NotFoundException;
import ru.manasyan.advertising.repository.SearchableRepository;
import ru.manasyan.advertising.util.Utils;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Service
public abstract class AbstractCrudService<E extends Deletable> implements CrudService<E> {
    private final SearchableRepository<E, Integer> repository;

    private final String entityName;

    @Override
    public void add(E entity) {
        validate(entity);
        int entityId = repository.save(entity).getId();
        log.info("{} #{} was added", entityName, entityId);
    }

    @Override
    @Transactional
    public void delete(int id) {
        E entity = getById(id);
        entity.setDeleted(true);
        log.info("{} #{} was deleted", entityName, id);
    }

    @Override
    @Transactional
    public void update(E entity) {
        if (!repository.existsById(entity.getId())) {
            throw new NotFoundException("Wrong id: " + entity.getId());
        }
        validate(entity);
        repository.save(entity);
        log.info("{} #{} was updated", entityName, entity.getId());
    }

    @Override
    public E getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Wrong id: " + id));
    }

    @Override
    public Iterable<SearchInfo> search(String template) {
        return repository.search(Utils.toSearchTemplate(template))
                .stream()
                .map(this::toSearchInfo)
                .collect(Collectors.toList());
    }

    protected abstract void validate(E entity);

    protected abstract SearchInfo toSearchInfo(E entity);
}
