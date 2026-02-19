package com.example.JobLink_backend.service;

import com.example.JobLink_backend.dto.UserDTO;
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
        user.setLogoUrl(userDTO.getLogoUrl());
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
                    return mapToDTO(userRepository.save(existing));
                })
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .uid(userDTO.getUid())
                            .email(userDTO.getEmail())
                            .name(userDTO.getName())
                            .skills(userDTO.getSkills())
                            .logoUrl(userDTO.getLogoUrl())
                            .build();
                    return mapToDTO(userRepository.save(newUser));
                });
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .uid(user.getUid())
                .email(user.getEmail())
                .name(user.getName())
                .skills(user.getSkills())
                .logoUrl(user.getLogoUrl())
                .build();
    }
}
