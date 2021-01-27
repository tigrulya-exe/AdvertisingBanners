package ru.manasyan.advertising.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.manasyan.advertising.data.entities.Banner;

import java.util.List;
import java.util.Optional;

@Repository
public interface BannerRepository extends SearchableRepository<Banner, Integer> {
    @Override
    @Query("select b from Banner b " +
            "where lower(b.name) like :#{#template} " +
            "and b.isDeleted = false")
    List<Banner> search(String template);

    @Query("select b from Banner b " +
            "where lower(b.category.requestName) like :#{#categoryName} " +
            "and b.isDeleted = false")
    Page<Banner> findByCategoryName(String categoryName, Pageable pageable);

    default Optional<Banner> findMostExpensiveByCategoryName(String categoryName) {
        return findByCategoryName(
                categoryName,
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "price"))
        ).stream().findAny();
    }
}
