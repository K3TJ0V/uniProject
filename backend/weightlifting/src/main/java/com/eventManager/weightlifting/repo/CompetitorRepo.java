package com.eventManager.weightlifting.repo;

import com.eventManager.weightlifting.model.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetitorRepo extends JpaRepository<Competitor, UUID> {
    @Query("SELECT c FROM Competitor c JOIN FETCH c.coach WHERE c.id = :id")
    public Optional<Competitor> getCompetitorWithCoach(@Param("id") UUID id);

    @Query("SELECT c FROM Competitor c JOIN FETCH c.coach")
    public List<Competitor> getAllCompetitorsWithCoaches();
}
