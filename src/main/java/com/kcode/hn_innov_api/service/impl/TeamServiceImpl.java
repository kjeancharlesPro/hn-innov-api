package com.kcode.hn_innov_api.service.impl;

import com.kcode.hn_innov_api.entity.ParticipantEntity;
import com.kcode.hn_innov_api.entity.TeamEntity;
import com.kcode.hn_innov_api.repository.TeamRepository;
import com.kcode.hn_innov_api.service.ParticipantService;
import com.kcode.hn_innov_api.service.TeamService;
import com.kcode.hn_innov_api.utils.TechTeamNameGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository repository;

    @Autowired
    private ParticipantService participantService;

    @Override
    @Transactional
    public List<TeamEntity> generateTeam() {
        List<ParticipantEntity> participants = participantService.getAll();
        if (participants == null || participants.isEmpty()) return Collections.emptyList();

        Collections.shuffle(participants);

        if (participants.size() == 4) {
            List<TeamEntity> teams = new ArrayList<>();

            TeamEntity team1 = new TeamEntity();
            team1.setName(TechTeamNameGenerator.generateTeamName());
            team1.addParticipant(participants.get(0));
            team1.addParticipant(participants.get(1));

            TeamEntity team2 = new TeamEntity();
            team2.setName(TechTeamNameGenerator.generateTeamName()); // correction ici
            team2.addParticipant(participants.get(2));
            team2.addParticipant(participants.get(3));

            teams.add(team1);
            teams.add(team2);

            return repository.saveAll(teams);
        }

        // Étape 1: distribution dans des équipes (sans nom pour l'instant)
        List<TeamEntity> teams = new ArrayList<>();
        for (ParticipantEntity p : participants) {
            addToBalancedTeam(teams, p); // addParticipant met bien la FK côté Participant
        }

        // Étape 2: fusion des équipes à 1 membre
        fixSingleMemberTeams(teams);

        // Étape 3: nommer et persister
        for (TeamEntity t : teams) {
            if (t.getName() == null || t.getName().isBlank()) {
                t.setName(TechTeamNameGenerator.generateTeamName());
            }
        }

        // Persist en base
        return repository.saveAll(teams);
    }

    @Override
    public List<TeamEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    private void addToBalancedTeam(List<TeamEntity> teams, ParticipantEntity p) {
        Optional<TeamEntity> target = teams.stream()
                .filter(t -> t.getParticipants().size() < 3)
                .min(Comparator.comparingInt(t -> t.getParticipants().size()));

        if (target.isPresent()) {
            target.get().addParticipant(p); // met à jour Participant.team (FK) et la liste
        } else {
            TeamEntity newTeam = new TeamEntity();
            newTeam.addParticipant(p);
            teams.add(newTeam);
        }
    }

    private void fixSingleMemberTeams(List<TeamEntity> teams) {
        // On copie d'abord pour éviter de modifier pendant l'itération principale
        List<TeamEntity> singles = teams.stream()
                .filter(t -> t.getParticipants().size() == 1)
                .toList();

        for (TeamEntity single : singles) {
            Optional<TeamEntity> target = teams.stream()
                    .filter(t -> t.getParticipants().size() >= 2)
                    .min(Comparator.comparingInt(t -> t.getParticipants().size()));

            if (target.isPresent()) {
                // Re-lier proprement chaque participant via addParticipant (FK mise à jour)
                for (ParticipantEntity p : new ArrayList<>(single.getParticipants())) {
                    // détacher proprement
                    single.removeParticipant(p);
                    // rattacher
                    target.get().addParticipant(p);
                }
                // retirer l'équipe vide
                teams.remove(single);
            }
        }
    }
}