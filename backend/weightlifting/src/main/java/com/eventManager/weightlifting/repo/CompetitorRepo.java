package com.eventManager.weightlifting.repo;

import com.eventManager.weightlifting.model.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompetitorRepo extends JpaRepository<Competitor, UUID> {
}
