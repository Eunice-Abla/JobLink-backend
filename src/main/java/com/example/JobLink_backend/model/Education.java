package com.example.JobLink_backend.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {
    private String title;
    private String company;
    private String date;
    private String description;
}
