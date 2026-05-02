package com.eventManager.weightlifting.repo;

import com.eventManager.weightlifting.model.Attempt;
import com.eventManager.weightlifting.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttemptRepo extends JpaRepository<Attempt, UUID> {

    @Query("SELECT DISTINCT a FROM Attempt a " +
           "LEFT JOIN FETCH a.judgeDecisions jd " +
           "LEFT JOIN FETCH jd.judge " +
           "JOIN FETCH a.competitor " +
           "WHERE a.event.id = :eventId")
    List<Attempt> findByEventIdWithDecisions(@Param("eventId") UUID eventId);

    Optional<Attempt> findByEvent_IdAndCompetitor_IdAndDisciplineAndAttemptNumber(
            UUID eventId, UUID competitorId, Discipline discipline, int attemptNumber);
}
