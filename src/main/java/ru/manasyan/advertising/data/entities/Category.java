package ru.manasyan.advertising.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

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

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @Where(clause = "isDeleted = false")
    private Set<Banner> banners;
}
