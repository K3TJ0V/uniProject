package com.eventManager.weightlifting.service;

import com.eventManager.weightlifting.model.Coach;
import com.eventManager.weightlifting.repo.CoachRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {
    private final CoachRepo coachRepo;

    public List<Coach> getAll(){
        return coachRepo.findAll();
    }
}
