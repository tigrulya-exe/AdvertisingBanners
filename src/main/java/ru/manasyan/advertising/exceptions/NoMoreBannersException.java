package ru.manasyan.advertising.exceptions;

public class NoMoreBannersException extends RuntimeException {
    public NoMoreBannersException(String categoryRequestName) {
        super("Banners in category " + categoryRequestName + " not found");
    }
}
