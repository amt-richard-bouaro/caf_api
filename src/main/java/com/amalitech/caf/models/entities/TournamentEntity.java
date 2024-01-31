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
@Table(name="tournament")
public class TournamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String edition;

  @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
//    @JoinColumn(name = "host_id")
    private List<HostEntity> hosts  ;
}
