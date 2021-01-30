package ru.manasyan.advertising.exceptions;

import lombok.Getter;

import java.util.Set;

@Getter
public class CategoryDeleteException extends RuntimeException {
    private final Set<Integer> bannerIds;

    public CategoryDeleteException(Set<Integer> bannerIds) {
        super(bannerIds.toString());
        this.bannerIds = bannerIds;
    }
}
