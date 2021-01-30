package ru.manasyan.advertising.data.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Requests")
public class Request extends Identifiable {
    @Type(type = "text")
    private String userAgent;

    private String ipAddress;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "bannerId")
    private Banner banner;
}
