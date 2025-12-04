package com.kcode.hn_innov_api.controller;


import com.kcode.hn_innov_api.service.JuryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jury-members")
@CrossOrigin(origins = "*")
public class JuryMemberController {

    @Autowired
    private JuryMemberService service;

    @GetMapping("/count")
    public long countJuryMembers() {
        return service.count();
    }
}
