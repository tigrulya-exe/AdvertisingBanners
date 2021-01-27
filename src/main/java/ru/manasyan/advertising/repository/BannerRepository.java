package ru.manasyan.advertising.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.manasyan.advertising.data.entities.Banner;

import java.util.List;

@Repository
public interface BannerRepository extends SearchableRepository<Banner, Integer> {
    @Override
    @Query("SELECT b from Banner b where lower(b.name) like :#{#template}")
    List<Banner> search(String template);
}
