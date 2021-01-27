package ru.manasyan.advertising.data.dto;

import lombok.Data;

@Data
public class CategoryDto implements Dto {
    private final String name;

    private final String requestName;
}
