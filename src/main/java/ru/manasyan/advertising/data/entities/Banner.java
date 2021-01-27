package ru.manasyan.advertising.data.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Banners")
public class Banner extends Deletable {
    private String name;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal price;

    @Type(type = "text")
    private String content;

    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
