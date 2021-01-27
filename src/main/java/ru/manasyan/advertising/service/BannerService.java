package ru.manasyan.advertising.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manasyan.advertising.data.dto.SearchInfo;
import ru.manasyan.advertising.data.entities.Banner;
import ru.manasyan.advertising.exceptions.NotFoundException;
import ru.manasyan.advertising.repository.BannerRepository;

import static ru.manasyan.advertising.util.Utils.toSearchTemplate;

@Service
public class BannerService extends AbstractCrudService<Banner> {
    public BannerService(BannerRepository repository) {
        super(repository);
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
        BannerRepository repository = (BannerRepository) getRepository();
        Banner banner = repository.findMostExpensiveByCategoryName(
                toSearchTemplate(categoryRequestName)
        ).orElseThrow(
                () -> new NotFoundException("No banners in category: " + categoryRequestName)
        );


        return null;
    }
}
