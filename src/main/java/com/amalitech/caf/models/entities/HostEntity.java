package com.amalitech.caf.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="host")
public class HostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    @ElementCollection
    private List<String> cities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private TournamentEntity tournament;
}
