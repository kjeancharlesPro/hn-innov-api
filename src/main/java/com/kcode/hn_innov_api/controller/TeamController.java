package com.kcode.hn_innov_api.controller;


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
    public void generate() {
        service.generateTeam();
    }
}
