package com.kcode.hn_innov_api.controller;


import com.kcode.hn_innov_api.entity.HackathonEntity;
import com.kcode.hn_innov_api.service.HackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hackathons")
@CrossOrigin(origins = "*")
public class HackathonController {

    @Autowired
    private HackathonService service;

    @PostMapping("/generate")
    private HackathonEntity createHackathon(){
        return service.create();
    }

    @DeleteMapping("/delete")
    private void deleteAll(){
        service.deleteAll();
    }
}
