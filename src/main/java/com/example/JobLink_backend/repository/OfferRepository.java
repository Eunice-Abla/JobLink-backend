package com.example.JobLink_backend.repository;

import com.example.JobLink_backend.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
    @Query("SELECT o FROM Offer o WHERE LOWER(o.title) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(o.company) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(o.location) LIKE LOWER(CONCAT('%', :q, '%'))")
    List<Offer> searchOffers(@Param("q") String q);
}
