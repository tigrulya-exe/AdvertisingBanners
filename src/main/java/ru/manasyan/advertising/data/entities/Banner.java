package ru.manasyan.advertising.data.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Banners")
public class Banner extends Deletable {
    private String name;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal price;

    @Type(type = "text")
    private String content;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
