package ru.manasyan.advertising.repository;

import org.springframework.data.repository.CrudRepository;
import ru.manasyan.advertising.data.entities.Request;

public interface RequestRepository extends CrudRepository<Request, Integer> {
}
