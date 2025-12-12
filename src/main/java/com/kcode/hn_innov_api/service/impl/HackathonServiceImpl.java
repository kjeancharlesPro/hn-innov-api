package com.kcode.hn_innov_api.service.impl;

import com.kcode.hn_innov_api.entity.*;
import com.kcode.hn_innov_api.repository.HackathonRepository;
import com.kcode.hn_innov_api.repository.JuryMemberRepository;
import com.kcode.hn_innov_api.service.HackathonService;
import com.kcode.hn_innov_api.service.JuryMemberService;
import com.kcode.hn_innov_api.service.StatusService;
import com.kcode.hn_innov_api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HackathonServiceImpl implements HackathonService {

    @Autowired
    private HackathonRepository repository;

    @Autowired
    private JuryMemberService juryMemberService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private StatusService statusService;

    @Override
    public HackathonEntity create() {

        StatusEntity status = statusService.getStatus();

        if (!status.getState().equals(StatusEnum.EN_PREPARATION)){
            throw new RuntimeException("STATUS INVALID");
        }

        JuryMemberEntity juryMembers = juryMemberService.getRandomJuryMember();
        List<TeamEntity> teams = teamService.generateTeam();

        HackathonEntity hackathon = new HackathonEntity(juryMembers,teams);

        return repository.save(hackathon);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
