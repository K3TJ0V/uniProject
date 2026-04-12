package com.eventManager.weightlifting.repo;

import com.eventManager.weightlifting.model.Judge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JudgeRepo extends JpaRepository<Judge, UUID> {
}
