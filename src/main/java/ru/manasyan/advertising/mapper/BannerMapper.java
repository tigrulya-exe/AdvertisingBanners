package ru.manasyan.advertising.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.manasyan.advertising.data.dto.BannerDto;
import ru.manasyan.advertising.data.entities.Banner;
import ru.manasyan.advertising.service.CategoryService;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class BannerMapper implements Mapper<Banner, BannerDto> {
    private final CategoryService categoryService;

    @Override
    public Banner toEntity(BannerDto dto) {
        return Banner.builder()
                .content(dto.getContent())
                .name(dto.getName())
                .category(categoryService.getById(
                        dto.getCategoryId()
                ))
                .price(BigDecimal.valueOf(
                        dto.getPrice()
                ))
                .build();
    }

}
