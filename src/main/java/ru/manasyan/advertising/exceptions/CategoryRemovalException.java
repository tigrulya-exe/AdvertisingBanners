package ru.manasyan.advertising.exceptions;

import java.util.Set;

public class CategoryRemovalException extends RuntimeException {
    public CategoryRemovalException(Set<Integer> bannerIds) {
        super("Can't delete category because it contains the following banners: " + bannerIds.toString());
    }
}
