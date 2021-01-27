package ru.manasyan.advertising.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Categories")
public class Category extends Deletable {
    private String name;

    private String requestName;
}
