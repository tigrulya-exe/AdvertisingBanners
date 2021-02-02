package ru.manasyan.advertising.data.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class BannerDto implements Dto {
    private final Integer id;

    @Size(min = 1, max = 255)
    @NotEmpty
    private final String name;

    @Digits(integer = 6, fraction = 2)
    @DecimalMin(value = "0.00")
    private final BigDecimal price;

    @NotNull
    private final Integer categoryId;

    @NotEmpty
    private final String content;
}
