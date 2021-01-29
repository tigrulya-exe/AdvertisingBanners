package ru.manasyan.advertising.data.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CategoryDto implements Dto {
    @Size(min = 1, max = 255)
    private final String name;

    @Size(min = 1, max = 255)
    private final String requestName;
}
