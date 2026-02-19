package com.example.JobLink_backend.util;

import com.example.JobLink_backend.model.Offer;
import com.example.JobLink_backend.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
// class dot le rôle est de charger les données de base dans la base de données
public class DataLoader implements CommandLineRunner {

    private final OfferRepository offerRepository;

    @Override
    public void run(String... args) throws Exception {
        if (offerRepository.count() == 0) {
            seedOffers();
        }
    }

    private void seedOffers() {
        Offer offer1 = Offer.builder()
                .title("Software Engineer")
                .company("TechCorp")
                .location("Lomé")
                .description("Nous recherchons un développeur Java/Spring passionné pour rejoindre notre équipe.")
                .matchPercentage(90)
                .contractType("CDI")
                .salary("500k - 800k")
                .logoUrl("https://via.placeholder.com/150")
                .skillsRequired(Arrays.asList("Java", "Spring Boot", "PostgreSQL"))
                .build();

        Offer offer2 = Offer.builder()
                .title("Flutter Developer")
                .company("MobileFirst")
                .location("Remote")
                .description("Expert Flutter capable de concevoir des interfaces fluides et performantes.")
                .matchPercentage(85)
                .contractType("Freelance")
                .salary("400k - 600k")
                .logoUrl("https://via.placeholder.com/150")
                .skillsRequired(Arrays.asList("Dart", "Flutter", "Firebase"))
                .build();

        Offer offer3 = Offer.builder()
                .title("Data Analyst")
                .company("DataInsights")
                .location("Cotonou")
                .description("Analyse de données et visualisation pour nos clients internationaux.")
                .matchPercentage(75)
                .contractType("CDI")
                .salary("600k - 900k")
                .logoUrl("https://via.placeholder.com/150")
                .skillsRequired(Arrays.asList("Python", "SQL", "Tableau"))
                .build();

        offerRepository.saveAll(Arrays.asList(offer1, offer2, offer3));
        System.out.println("Sample job offers seeded successfully.");
    }
}
