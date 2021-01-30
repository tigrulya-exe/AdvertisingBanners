package ru.manasyan.advertising.data.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CategoryDto implements Dto {
    private final Integer id;

    @Size(min = 1, max = 255)
    @NotEmpty
    private final String name;

    @Size(min = 1, max = 255)
    @NotEmpty
    private final String requestName;
}
