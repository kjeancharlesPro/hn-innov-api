package com.kcode.hn_innov_api.service.impl;

import com.kcode.hn_innov_api.controller.TeamController;
import com.kcode.hn_innov_api.entity.ParticipantEntity;
import com.kcode.hn_innov_api.entity.TeamEntity;
import com.kcode.hn_innov_api.repository.TeamRepository;
import com.kcode.hn_innov_api.service.ParticipantService;
import com.kcode.hn_innov_api.service.TeamService;
import com.kcode.hn_innov_api.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository repository;

    @Autowired
    private ParticipantService participantService;

    @Override
    public void generateTeam() {
        List<ParticipantEntity> participants = participantService.getAll();
        Collections.shuffle(participants); // mélange aléatoire

        List<TeamEntity> teams = new ArrayList<>();

        for(int i = 0 ; i < participants.size();i++) {
            if((i+1) % 2 == 0 ){
                List<String> strings = new ArrayList<>();
                strings.add(participants.get(i).getFirstName().charAt(0)+ ". "+ participants.get(i).getLastName());
                strings.add(participants.get(i - 1).getFirstName().charAt(0)+ " "+ participants.get(i).getLastName());

                TeamEntity team = new TeamEntity();
                team.setName(RandomGenerator.teamName());
                team.setMembers(strings);
                teams.add(team);
            }
        }
        teams.forEach(team -> repository.save(team));

    }

}
