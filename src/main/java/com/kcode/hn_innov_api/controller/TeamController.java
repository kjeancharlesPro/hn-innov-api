package com.kcode.hn_innov_api.controller;


import com.kcode.hn_innov_api.entity.ParticipantEntity;
import com.kcode.hn_innov_api.entity.TeamEntity;
import com.kcode.hn_innov_api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "*")
public class TeamController {

    @Autowired
    private TeamService service;

    @GetMapping("/generate")
    public List<TeamEntity> generate() {
        return service.generateTeam();
    }

    @GetMapping("/all")
    public List<TeamEntity> getAll() {
        return service.getAll();
    }

    @DeleteMapping
    private void deleteAll(){
        service.deleteAll();
    }
}
