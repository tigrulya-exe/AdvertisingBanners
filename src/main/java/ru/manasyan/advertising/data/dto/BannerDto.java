package ru.manasyan.advertising.data.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BannerDto implements Dto {
    private final Integer id;

    @Size(min = 1, max = 255)
    @NotEmpty
    private final String name;

    @NotNull
    private final Float price;

    @NotNull
    private final Integer categoryId;

    @NotEmpty
    private final String content;
}
