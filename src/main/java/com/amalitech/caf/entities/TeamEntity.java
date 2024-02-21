package com.amalitech.caf.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team")
//@Builder
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String flag;
    private String coach;
    
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
//    @JoinColumn(name = "team_id")
    private List<PlayerEntity> players;
    
    
}
