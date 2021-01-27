package ru.manasyan.advertising.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.manasyan.advertising.data.entities.Request;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Integer> {

//    @Query("select r from Request r " +
//            "where r.userAgent = :#{#userAgent} " +
//            "and r.ipAddress = :#{#ipAddress} " +
//            "and "
//    )
//    List<Request> findTodaysUserRequests(String userAgent, String ipAddress);
}
