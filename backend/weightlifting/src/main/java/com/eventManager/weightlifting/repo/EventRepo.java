package com.eventManager.weightlifting.repo;

import com.eventManager.weightlifting.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventRepo extends JpaRepository<Event, UUID> {
    @Query("SELECT e FROM Event e JOIN FETCH e.judges")
    List<Event> getAllWithJudges();

    @Query("SELECT e FROM Event e JOIN FETCH e.competitors")
    List<Event> getAllWithCompetitors();
}
