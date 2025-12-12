package com.kcode.hn_innov_api.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipantEntity> participants = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "hackathon_id")
    private HackathonEntity hackathon;

    // Méthode utilitaire pour gérer les deux côtés
    public void addParticipant(ParticipantEntity participant) {
        participants.add(participant);
        participant.setTeam(this);
    }

    public void removeParticipant(ParticipantEntity participant) {
        participants.remove(participant);
        participant.setTeam(null);
    }
}
