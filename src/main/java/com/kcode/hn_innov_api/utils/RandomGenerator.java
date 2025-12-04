package com.kcode.hn_innov_api.utils;

import com.kcode.hn_innov_api.entity.ParticipantEntity;

import java.util.*;

public class RandomGenerator {

    private static final List<String> TEAM_NAMES = Arrays.asList(
            "Code Warriors", "Bug Busters", "Hack Masters",
            "Algo Ninjas", "Data Pirates", "Java Avengers"
    );

    public static String teamName() {
        Random rand = new Random();
        return TEAM_NAMES.get(rand.nextInt(TEAM_NAMES.size()));
    }

    // Générer des équipes aléatoires par compétence
    public static List<List<String>> participants(List<ParticipantEntity> participants) {
        List<String> team = new ArrayList<>();

        for (int i = 0; i < participants.size(); i += 3) {
            int end = Math.min(i + 3, participants.size());
            team.add(String.valueOf(new ArrayList<>(participants.subList(i, end))));
        }

        return Collections.singletonList(team);
    }
}
