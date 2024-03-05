package com.amalitech.caf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;


@Entity
@Table(name = "stadium")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StadiumEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "host")
    private HostEntity host;

    private String city;

    private String image;

    private String name;

    private Long capacity;

}
