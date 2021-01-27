package ru.manasyan.advertising.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Categories")
public class Category extends Deletable {
    public Category(String name, String requestName) {
        this.name = name;
        this.requestName = requestName;
    }

    private String name;

    private String requestName;
}
