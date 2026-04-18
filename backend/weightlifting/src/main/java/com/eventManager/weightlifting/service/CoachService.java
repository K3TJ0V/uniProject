package com.eventManager.weightlifting.service;

import com.eventManager.weightlifting.dto.request.CoachRequest;
import com.eventManager.weightlifting.dto.response.CoachResponse;
import com.eventManager.weightlifting.model.Coach;
import com.eventManager.weightlifting.repo.CoachRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public CoachResponse getSingleCoach(UUID id){
        Coach coach = coachRepo.findById(id).orElse(null);
        CoachResponse res = null;

        if(coach != null){
            res = CoachResponse.builder()
                    .id(coach.getId())
                    .firstName(coach.getFirstName())
                    .lastName(coach.getLastName())
                    .dateOfBirth(coach.getDateOfBirth())
                    .team(coach.getTeam())
                    .build();
        }

        return res;
    }

    public CoachResponse upsertCoach(CoachRequest body, UUID id){
        Coach coach = new Coach();
        if(id != null){
            coach = coachRepo.findById(id).orElse(new Coach());
        }
        coach.setId(id);
        coach.setFirstName(body.getFirstName());
        coach.setLastName(body.getLastName());
        coach.setDateOfBirth(body.getDateOfBirth());
        coach.setTeam(body.getTeam());

        Coach saved = coachRepo.save(coach);

        return CoachResponse.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .dateOfBirth(saved.getDateOfBirth())
                .team(saved.getTeam())
                .build();
    }

    public void deleteCoach(UUID id){
        Optional<Coach> coach = coachRepo.findById(id);
        coach.ifPresent(coachRepo::delete);
    }
}
