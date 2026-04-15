package com.eventManager.weightlifting.service;

import com.eventManager.weightlifting.dto.request.CoachRequest;
import com.eventManager.weightlifting.dto.response.CoachResponse;
import com.eventManager.weightlifting.model.Coach;
import com.eventManager.weightlifting.repo.CoachRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoachService {
    private final CoachRepo coachRepo;

    public List<CoachResponse> getAll(){
        return coachRepo.findAll().stream()
                .map(coach -> {
                    return CoachResponse.builder()
                            .id(coach.getId())
                            .firstName(coach.getFirstName())
                            .lastName(coach.getLastName())
                            .dateOfBirth(coach.getDateOfBirth())
                            .team(coach.getTeam())
                            .build();
                }).toList();
    }

    public CoachResponse upsertCoach(CoachRequest coach, UUID id){

    }
}
