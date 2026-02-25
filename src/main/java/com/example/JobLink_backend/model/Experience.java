package com.example.JobLink_backend.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experience {
    private String title;
    private String description;
    private String company;
    private String date;
}
