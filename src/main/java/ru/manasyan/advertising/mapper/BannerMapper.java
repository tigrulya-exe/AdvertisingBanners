package ru.manasyan.advertising.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.manasyan.advertising.data.dto.BannerDto;
import ru.manasyan.advertising.data.entities.Banner;
import ru.manasyan.advertising.service.CategoryService;

@RequiredArgsConstructor
@Component
public class BannerMapper implements Mapper<Banner, BannerDto> {
    private final CategoryService categoryService;

    @Override
    public Banner toEntity(BannerDto dto) {
        Banner banner = Banner.builder()
                .content(dto.getContent())
                .name(dto.getName())
                .category(categoryService.getById(
                        dto.getCategoryId()
                ))
                .price(dto.getPrice())
                .build();

        if (dto.getId() != null) {
            banner.setId(dto.getId());
        }
        return banner;
    }

    @Override
    public BannerDto toDto(Banner entity) {
        return new BannerDto(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getCategory().getId(),
                entity.getContent()
        );
    }

}
