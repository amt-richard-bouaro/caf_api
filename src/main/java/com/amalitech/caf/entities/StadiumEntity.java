package com.amalitech.caf.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "stadium")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StadiumEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String image;

    private String name;

    private Long capacity;

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Long getCapacity() {
        return capacity;
    }
}
