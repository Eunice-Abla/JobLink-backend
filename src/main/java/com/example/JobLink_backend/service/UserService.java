package com.example.JobLink_backend.service;

import com.example.JobLink_backend.dto.UserDTO;
import com.example.JobLink_backend.model.Education;
import com.example.JobLink_backend.model.Experience;
import com.example.JobLink_backend.model.User;
import com.example.JobLink_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<UserDTO> getUserProfile(String uid) {
        return userRepository.findById(uid).map(this::mapToDTO);
    }

    @Transactional
    public UserDTO updateProfile(String uid, UserDTO userDTO) {
        User user = userRepository.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDTO.getName());
        user.setProfession(userDTO.getProfession());
        user.setCvUrl(userDTO.getCvUrl());
        user.setPhotoUrl(userDTO.getPhotoUrl());
        user.setBirthDate(userDTO.getBirthDate());
        user.setAddress(userDTO.getAddress());
        user.setLinkedinUrl(userDTO.getLinkedinUrl());
        user.setPortfolioUrl(userDTO.getPortfolioUrl());
        user.setPortfolioDescription(userDTO.getPortfolioDescription());
        user.setSkills(userDTO.getSkills());
        user.setPreferences(userDTO.getPreferences());
        user.setExperiences(userDTO.getExperiences());
        user.setEducation(userDTO.getEducation());
        return mapToDTO(userRepository.save(user));
    }

    @Transactional
    public UserDTO updateSkills(String uid, List<String> skills) {
        User user = userRepository.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        user.setSkills(skills);
        return mapToDTO(userRepository.save(user));
    }

    @Transactional
    public UserDTO registerOrSync(UserDTO userDTO) {
        return userRepository.findById(userDTO.getUid())
                .map(existing -> {
                    existing.setEmail(userDTO.getEmail());
                    existing.setName(userDTO.getName());
                    // Keep existing other fields or update if provided
                    return mapToDTO(userRepository.save(existing));
                })
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .uid(userDTO.getUid())
                            .email(userDTO.getEmail())
                            .name(userDTO.getName())
                            .profession(userDTO.getProfession())
                            .cvUrl(userDTO.getCvUrl())
                            .photoUrl(userDTO.getPhotoUrl())
                            .skills(userDTO.getSkills())
                            .preferences(userDTO.getPreferences())
                            .experiences(userDTO.getExperiences())
                            .education(userDTO.getEducation())
                            .build();
                    return mapToDTO(userRepository.save(newUser));
                });
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .uid(user.getUid())
                .email(user.getEmail())
                .name(user.getName())
                .profession(user.getProfession())
                .cvUrl(user.getCvUrl())
                .photoUrl(user.getPhotoUrl())
                .birthDate(user.getBirthDate())
                .address(user.getAddress())
                .linkedinUrl(user.getLinkedinUrl())
                .portfolioUrl(user.getPortfolioUrl())
                .portfolioDescription(user.getPortfolioDescription())
                .skills(user.getSkills())
                .preferences(user.getPreferences())
                .experiences(user.getExperiences())
                .education(user.getEducation())
                .build();
    }
}
