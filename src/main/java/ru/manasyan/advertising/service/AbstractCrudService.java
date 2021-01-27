package ru.manasyan.advertising.service;

import org.springframework.stereotype.Service;
import ru.manasyan.advertising.data.entities.Identifiable;

@Service
public abstract class AbstractCrudService<E extends Identifiable> {
}
