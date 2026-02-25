package com.example.JobLink_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String uid; // Firebase UID

    @Column(nullable = false, unique = true)
    private String email;

    private String name;
    private String profession;
    private String cvUrl;
    private String photoUrl;
    private String birthDate;
    private String address;
    private String linkedinUrl;
    private String portfolioUrl;

    @Column(length = 1000)
    private String portfolioDescription;

    @ElementCollection
    @CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_preferences", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "preference")
    private List<String> preferences = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_experiences", joinColumns = @JoinColumn(name = "user_id"))
    private List<Experience> experiences = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_education", joinColumns = @JoinColumn(name = "user_id"))
    private List<Education> education = new ArrayList<>();
}
