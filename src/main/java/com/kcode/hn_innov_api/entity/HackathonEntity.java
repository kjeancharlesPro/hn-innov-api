package com.kcode.hn_innov_api.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hackathon")
public class HackathonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private JuryMemberEntity juryMember;

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL)
    private List<TeamEntity> teams;

    public HackathonEntity(JuryMemberEntity juryMember, List<TeamEntity> teams) {
        this.juryMember = juryMember;
        this.teams = teams;
    }
}
