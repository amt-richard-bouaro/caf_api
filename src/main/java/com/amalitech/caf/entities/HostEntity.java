package com.amalitech.caf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "host")
public class HostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StadiumEntity> cities = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private TournamentEntity tournament;
}
