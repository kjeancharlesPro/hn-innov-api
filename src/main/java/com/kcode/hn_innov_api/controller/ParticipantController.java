package com.kcode.hn_innov_api.controller;


import com.kcode.hn_innov_api.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participants")
@CrossOrigin(origins = "*")
public class ParticipantController {
    @Autowired
    private ParticipantService service;

    @DeleteMapping("/delete")
    private void deleteAll(){
        service.deleteAll();
    }
}
