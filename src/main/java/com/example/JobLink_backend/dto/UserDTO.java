package com.example.JobLink_backend.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String uid;
    private String email;
    private String name;
    private List<String> skills;
    private String logoUrl;
}
