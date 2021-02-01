package ru.manasyan.advertising.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manasyan.advertising.data.dto.SearchInfo;
import ru.manasyan.advertising.data.entities.Banner;
import ru.manasyan.advertising.exceptions.AlreadyExistsException;
import ru.manasyan.advertising.exceptions.NoMoreBannersException;
import ru.manasyan.advertising.repository.BannerRepository;

import java.util.Set;

@Slf4j
@Service
public class BannerService extends AbstractCrudService<Banner> {
    private static final String ENTITY_NAME = "Banner";

    private final RequestService requestService;

    public BannerService(
            BannerRepository repository,
            RequestService requestService
    ) {
        super(repository, ENTITY_NAME);
        this.requestService = requestService;
    }

    @Override
    protected SearchInfo toSearchInfo(Banner entity) {
        return new SearchInfo(
                entity.getId(),
                entity.getName()
        );
    }

    @Transactional
    public Banner getByCategory(
            String categoryRequestName,
            String userAgent,
            String ipAddress
    ) {
        log.info(
                "{} banner request by [{}, {}]",
                categoryRequestName,
                userAgent,
                ipAddress
        );

        BannerRepository repository = getRepository();
        Set<Banner> todaysUserBanners = requestService.findTodaysUserBanners(
                categoryRequestName,
                userAgent,
                ipAddress
        );

        try (var banners = repository.findMostExpensiveInCategory(
                categoryRequestName
        )) {
            Banner banner = banners.dropWhile(todaysUserBanners::contains)
                    .findFirst()
                    .orElseThrow(() -> new NoMoreBannersException(
                            categoryRequestName
                    ));
            requestService.create(banner, userAgent, ipAddress);
            log.info("Successful request: return banner #{}", banner.getId());
            return banner;
        }
    }

    @Override
    protected void validate(Banner entity) {
        getRepository().findByName(entity.getName())
                .filter(c -> c.getId() != entity.getId())
                .ifPresent(c -> {
                    throw new AlreadyExistsException(
                            "name", entity.getName()
                    );
                });
    }

    @Override
    protected BannerRepository getRepository() {
        return (BannerRepository) super.getRepository();
    }
}
