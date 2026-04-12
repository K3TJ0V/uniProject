package com.eventManager.weightlifting.repo;

import com.eventManager.weightlifting.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoachRepo extends JpaRepository<Coach, UUID> {
}
