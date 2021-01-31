package ru.manasyan.advertising.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manasyan.advertising.data.dto.SearchInfo;
import ru.manasyan.advertising.data.entities.Banner;
import ru.manasyan.advertising.exceptions.AlreadyExistsException;
import ru.manasyan.advertising.exceptions.NotFoundException;
import ru.manasyan.advertising.repository.BannerRepository;

import java.util.Set;

@Service
public class BannerService extends AbstractCrudService<Banner> {
    private final RequestService requestService;

    public BannerService(
            BannerRepository repository,
            RequestService requestService
    ) {
        super(repository);
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
                    .orElseThrow(() -> new NotFoundException(
                            "Banners in category " + categoryRequestName + " not found"
                    ));

            requestService.create(banner, userAgent, ipAddress);
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
