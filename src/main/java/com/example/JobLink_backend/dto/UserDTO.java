package com.example.JobLink_backend.dto;

import com.example.JobLink_backend.model.Education;
import com.example.JobLink_backend.model.Experience;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @JsonProperty("id")
    private String uid;
    private String email;
    private String name;
    private String profession;
    private String cvUrl;
    private String photoUrl;
    private String birthDate;
    private String address;
    private String linkedinUrl;
    private String portfolioUrl;
    private String portfolioDescription;
    private List<String> skills;
    private List<String> preferences;
    private List<Experience> experiences;
    private List<Education> education;
}
