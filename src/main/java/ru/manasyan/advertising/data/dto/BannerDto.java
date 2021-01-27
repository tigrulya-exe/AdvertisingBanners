package ru.manasyan.advertising.data.dto;

import lombok.Data;

@Data
public class BannerDto implements Dto {
    private final String name;

    private final long price;

    private final int categoryId;

    private final String content;
}
