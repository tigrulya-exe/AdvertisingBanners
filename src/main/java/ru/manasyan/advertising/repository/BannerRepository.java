package ru.manasyan.advertising.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.manasyan.advertising.data.entities.Banner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Repository
public interface BannerRepository extends SearchableRepository<Banner, Integer> {
    @Override
    @Query("select b from Banner b " +
            "where lower(b.name) like :#{#template} " +
            "and b.isDeleted = false")
    List<Banner> search(String template);

    @Query("select b from Banner b " +
            "where lower(b.category.requestName) like :#{#categoryName} " +
            "and b.isDeleted = false " +
            "order by b.price desc")
    Stream<Banner> findMostExpensiveInCategory(String categoryName);

    @Query("select b from Banner b " +
            "join Request r on r.banner.id = b.id " +
            "where b.isDeleted = false " +
            "and b.category.requestName = :#{#categoryName} " +
            "and r.userAgent = :#{#userAgent} " +
            "and r.ipAddress = :#{#ipAddress} " +
            "and r.date > :#{#date}")
    Set<Banner> findBannersByUserAgentAndIpNewerThan(
            String userAgent,
            String ipAddress,
            String categoryName,
            LocalDateTime date
    );

    default Set<Banner> findTodaysBannersByUserAgentAndIp(
            String userAgent,
            String ipAddress,
            String categoryName
    ) {
        return findBannersByUserAgentAndIpNewerThan(
                userAgent,
                ipAddress,
                categoryName,
                LocalDateTime.now().minus(1, ChronoUnit.DAYS)
        );
    }

    Optional<Banner> findByName(String name);
}
