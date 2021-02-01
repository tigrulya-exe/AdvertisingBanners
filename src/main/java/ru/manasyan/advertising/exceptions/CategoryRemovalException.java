package ru.manasyan.advertising.exceptions;

import lombok.Getter;

import java.util.Set;

@Getter
public class CategoryRemovalException extends RuntimeException {
    private final Set<Integer> bannerIds;

    public CategoryRemovalException(Set<Integer> bannerIds) {
        super(bannerIds.toString());
        this.bannerIds = bannerIds;
    }
}
