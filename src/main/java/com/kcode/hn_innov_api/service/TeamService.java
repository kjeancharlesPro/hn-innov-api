package com.kcode.hn_innov_api.service;

import com.kcode.hn_innov_api.entity.ParticipantEntity;
import com.kcode.hn_innov_api.entity.TeamEntity;

import java.util.List;

public interface TeamService {

    List<TeamEntity> generateTeam();

    List<TeamEntity> getAll();

    void deleteAll();
}
