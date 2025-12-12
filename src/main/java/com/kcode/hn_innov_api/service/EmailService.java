package com.kcode.hn_innov_api.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendInvitation(String email) throws MessagingException;

    void sendPreInvitation(String email);

    void sendInvitations();
}
