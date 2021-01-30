package ru.manasyan.advertising.mapper;

import ru.manasyan.advertising.data.dto.Dto;
import ru.manasyan.advertising.data.entities.Identifiable;

public interface Mapper<E extends Identifiable, D extends Dto> {
    E toEntity(D dto);

    D toDto(E entity);
}
