package com.example.JobLink_backend.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.config.path}")
    private Resource firebaseConfig;

    @PostConstruct
    public void initialize() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(firebaseConfig.getInputStream()))
                        .build();

                FirebaseApp.initializeApp(options);
                System.out.println("Firebase has been initialized");
            }
        } catch (IOException e) {
            System.err.println("Error initializing Firebase: " + e.getMessage());
            // In a real app, you might want to throw a RuntimeException here
        }
    }
}
