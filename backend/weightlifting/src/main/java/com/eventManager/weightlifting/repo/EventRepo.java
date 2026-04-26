package com.eventManager.weightlifting.repo;

import com.eventManager.weightlifting.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepo extends JpaRepository<Event, UUID> {
}
