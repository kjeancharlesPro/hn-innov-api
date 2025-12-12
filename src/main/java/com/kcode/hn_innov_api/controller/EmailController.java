package com.kcode.hn_innov_api.controller;

import com.kcode.hn_innov_api.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mails")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendInvitation/{email}")
    public void sendInvitation(@PathVariable String email) throws MessagingException {
        emailService.sendInvitation(email);
    }

    @GetMapping("/sendPreInvitation/{email}")
    public void sendPreInvitation(@PathVariable String email) {
        emailService.sendPreInvitation(email);
    }

    @GetMapping("/sendInvitations")
    public void sendInvitations()  {
        emailService.sendInvitations();
    }

}
