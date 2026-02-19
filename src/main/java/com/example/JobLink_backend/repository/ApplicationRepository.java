package com.example.JobLink_backend.repository;

import com.example.JobLink_backend.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String> {
    List<Application> findByUserId(String userId);
}
