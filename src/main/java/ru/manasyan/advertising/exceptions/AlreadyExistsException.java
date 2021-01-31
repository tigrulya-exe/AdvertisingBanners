package ru.manasyan.advertising.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String field, String fieldValue) {
        super("Entity with " + field + " '" + fieldValue + "' already exists.");
    }
}
