package com.kcode.hn_innov_api.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TechTeamNameGenerator {

    private static final List<String> PREFIXES = Arrays.asList(
            // Tech / Futuriste
            "Quantum", "Cyber", "Data", "Nano", "Pixel", "Code", "Neuro", "Robo",
            "Virtual", "Smart", "Deep", "Hyper", "Digital", "Bio", "Techno", "Info",
            // Énergie / Puissance
            "Turbo", "Electro", "Fusion", "Solar", "Atomic", "Storm", "Laser", "Magnet", "Volt", "Ion",
            "Pulse", "Photon", "Plasma", "Ignite", "Spark", "Nova", "Inferno", "Blaze", "Shock", "Core",
            // Exploration / Aventure
            "Cosmo", "Astro", "Geo", "Terra", "Ocean", "Sky", "Lunar", "Solaris", "Orbit", "Galaxy",
            "Star", "Comet", "Meteor", "Eclipse", "Aurora", "Nebula", "Horizon", "Voyager", "Explorer", "Frontier"
    );

    private static final List<String> SUFFIXES = Arrays.asList(
            // Équipes / Groupes
            "Warriors", "Hackers", "Builders", "Squad", "Ninjas", "Masters", "Engineers", "Explorers", "Innovators", "Architects",
            "Creators", "Pioneers", "Guardians", "Hunters", "Seekers", "Defenders", "Riders", "Champions", "Coders", "Technicians",
            // Style / Attitude
            "Overlords", "Titans", "Giants", "Phantoms", "Shadows", "Dragons", "Samurais", "Pirates", "Wizards", "Alchemists",
            "Rangers", "Knights", "Gladiators", "Raiders", "Commanders", "Leaders", "Strategists", "Visionaries", "Inventors", "Creators",
            // Modern / Startup vibe
            "Makers", "Thinkers", "Doers", "Dreamers", "Innovators", "Creators", "Disruptors", "Hacktivists", "Technologists"
    );

    private static final Random random = new Random();

    public static String generateTeamName() {
        String prefix = PREFIXES.get(random.nextInt(PREFIXES.size()));
        String suffix = SUFFIXES.get(random.nextInt(SUFFIXES.size()));
        return prefix + " " + suffix;
    }


}
