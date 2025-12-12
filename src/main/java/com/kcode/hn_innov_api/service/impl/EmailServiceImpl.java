package com.kcode.hn_innov_api.service.impl;

import com.kcode.hn_innov_api.entity.PeriodEntity;
import com.kcode.hn_innov_api.service.EmailService;
import com.kcode.hn_innov_api.service.JuryMemberService;
import com.kcode.hn_innov_api.service.ParticipantService;
import com.kcode.hn_innov_api.service.PeriodService;
import com.kcode.hn_innov_api.utils.IcsGenerator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private JuryMemberService juryMemberService;

    @Autowired
    private PeriodService periodService;

    @Override
    public void sendInvitations() {
        List<String> emails = participantService.getAllEmail();

        emails.addAll(juryMemberService.getAllEmail());

        emails.forEach(this::sendPreInvitation);
    }

    @Override
    public void sendInvitation(String email) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("hackathonhn@gmail.com");
        helper.setTo(email);
        helper.setSubject("Invitation Teams - Lancement Hackathon");
        helper.setText("Bonjour,\n\nVoici les invitations pour les réunions de lancement et cloture du hackathon.\n\n");

        PeriodEntity period = periodService.getClosestPeriodFromNow();

        ZonedDateTime firstInviteStart = period.getStartDate().atZone(ZoneId.of("Europe/Paris"));
        ZonedDateTime firstInviteEnd = firstInviteStart.plusMinutes(30);

        String ics1 = IcsGenerator.generateIcs(
                "Invitation Teams - Lancement Hackathon",
                "Lien Teams: https://teams.microsoft.com/l/meetup-join/...",
                "Microsoft Teams",
                firstInviteStart,
                firstInviteEnd
        );

        ZonedDateTime secondInviteStart = period.getEndDate().atZone(ZoneId.of("Europe/Paris"));
        ZonedDateTime secondInviteSEnd = secondInviteStart.plusHours(1);

        String ics2 = IcsGenerator.generateIcs(
                "Invitation Teams - Cloture Hackathon",
                "Lien Teams: https://teams.microsoft.com/l/meetup-join/...",
                "Microsoft Teams",
                secondInviteStart,
                secondInviteSEnd
        );
        helper.addAttachment("invitation-lancement.ics", new ByteArrayResource(ics1.getBytes()));
        helper.addAttachment("invitation-cloture.ics", new ByteArrayResource(ics2.getBytes()));
        mailSender.send(message);
    }

    @Override
    public void sendPreInvitation(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hackathonhn@gmail.com");
        message.setTo(email);
        message.setSubject("Invitation Teams - Inscription Hackathon");
        message.setText("Bonjour,\n" +
                "Le hackathon est sur le point de commencer.\n" +
                "Préparez vous : l’aventure démarre bientôt.\n" +
                "Vous serez notifiés automatiquement dès que le hackathon sera officiellement lancé afin de ne rien manquer.\n" +
                "À très vite pour cette expérience unique d’innovation et de collaboration !\n");
        mailSender.send(message);

    }
}
