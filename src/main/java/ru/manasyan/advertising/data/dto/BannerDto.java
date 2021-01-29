package ru.manasyan.advertising.data.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class BannerDto implements Dto {
    @Size(min = 1, max = 255)
    private final String name;

    private final long price;

    private final int categoryId;

    @NotEmpty
    private final String content;
}
