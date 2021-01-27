package ru.manasyan.advertising.service;

import org.springframework.stereotype.Service;
import ru.manasyan.advertising.data.dto.SearchInfo;
import ru.manasyan.advertising.data.entities.Banner;
import ru.manasyan.advertising.repository.BannerRepository;

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
}
