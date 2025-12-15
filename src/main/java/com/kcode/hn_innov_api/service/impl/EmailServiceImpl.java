package com.kcode.hn_innov_api.service.impl;

import com.kcode.hn_innov_api.entity.JuryMemberEntity;
import com.kcode.hn_innov_api.entity.ParticipantEntity;
import com.kcode.hn_innov_api.entity.PeriodEntity;
import com.kcode.hn_innov_api.service.EmailService;
import com.kcode.hn_innov_api.service.JuryMemberService;
import com.kcode.hn_innov_api.service.ParticipantService;
import com.kcode.hn_innov_api.service.PeriodService;
import com.kcode.hn_innov_api.utils.IcsGenerator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @SneakyThrows
    @Override
    public void sendInvitations() {
        List<String> emails = participantService.getAllEmail();
        emails.addAll(juryMemberService.getAllEmail());

        emails.forEach(s -> {
            try {
                sendInvitation(s);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void sendInvitation(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        PeriodEntity period = periodService.getClosestPeriodFromNow();

        ZonedDateTime firstInviteStart = period.getStartDate().atZone(ZoneId.of("Europe/Paris"));
        ZonedDateTime firstInviteEnd = firstInviteStart.plusMinutes(30);

        String text = getText(email, firstInviteStart, firstInviteEnd);

        helper.setFrom("hackathonhn@gmail.com");
        helper.setTo(email);
        helper.setSubject("Invitation Teams - Lancement Hackathon");
        helper.setText(text);


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


    private String getText(String email, ZonedDateTime firstInviteStart, ZonedDateTime firstInviteEnd) {

        ParticipantEntity participant = participantService.getByEmail(email);
        JuryMemberEntity juryMember = juryMemberService.getByEmail(email);
        String name = "";
        if (participant != null) {
            name =  " "+participant.getFirstName();
        } else if(juryMember != null) {
            name = " "+juryMember.getFirstName();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String messageTemplate = """
            Bonjour%s,
            Nous sommes ravis de vous confirmer votre inscription a la 2ème édition du HN Hackaton ! 🎉
            Le Hackaton se déroulera le %s au %s. N’oubliez pas de venir le premier jour à %s.
            Vous trouverez les règles à respecter ici %s.
            Le sujet à traiter vous sera dévoilé le jour J. Préparez-vous à relever un défi passionnant !
            Pour retrouver toutes les informations utiles (règles, équipes, sujet à traiter, etc.), rendez-vous sur le site officiel du Hackaton : %s.
            Pensez à apporter un ordinateur, un chargeur et beaucoup d’énergie pour une expérience optimale !
            Nous avons hâte de vous retrouver pour ce moment d’innovation et de collaboration. En cas de question, n’hésitez pas à nous contacter.
            Bonne préparation, et à très vite !
            
            Cordialement,
            %s
            %s
            %s
            """;

        return String.format(
                messageTemplate,
                name,                        // %s → Prénom
                firstInviteStart.format(formatter),                   // %s → Date
                "14 Place de la Coupole, 94220 Charenton-le-Pont",// %s → Adresse
                "14h30",                        // %s → Heure de début
                "https://kjeancharlespro.github.io/hn-innov-ui/reglement",  // %s → Lien vers les règles
                "https://kjeancharlespro.github.io/hn-innov-ui/",         // %s → Lien site officiel
                "Équipe Organisation",          // %s → Nom organisateur
                "hackathonhn@gmail.com",         // %s → Email
                "https://kjeancharlespro.github.io/hn-innov-ui/"   // %s → Site web / réseaux sociaux
        );
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
