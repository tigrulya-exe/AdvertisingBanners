package ru.manasyan.advertising.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.manasyan.advertising.data.dto.Dto;
import ru.manasyan.advertising.data.entities.Identifiable;
import ru.manasyan.advertising.mapper.Mapper;
import ru.manasyan.advertising.service.CrudService;

@RequiredArgsConstructor
public class AbstractCrudController<E extends Identifiable, D extends Dto> {
    private final CrudService<E> service;

    private final Mapper<E, D> mapper;

    // TODO: add validation
    @PostMapping
    public ResponseEntity<?> create(@RequestBody D dto) {
        service.add(mapper.toEntity(dto));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> search(@RequestParam String template) {
        return ResponseEntity.ok(
                service.search(template)
        );
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody D dto) {
        service.update(mapper.toEntity(dto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

