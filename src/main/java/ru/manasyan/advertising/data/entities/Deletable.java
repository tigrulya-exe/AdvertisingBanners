package ru.manasyan.advertising.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class Deletable extends Identifiable {
    private boolean isDeleted;
}
