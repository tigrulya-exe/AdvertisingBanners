package ru.manasyan.advertising.data.dto;

import lombok.Data;

@Data
public class ErrorDto {
    public enum ErrorType {
        VALIDATION_FAIL,
        ALREADY_EXISTS,
        NOT_FOUND,
        CATEGORY_DELETE
    }

    private final ErrorType type;

    private final String message;
}
