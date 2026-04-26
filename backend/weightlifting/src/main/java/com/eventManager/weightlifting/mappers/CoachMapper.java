package com.eventManager.weightlifting.mappers;

import com.eventManager.weightlifting.dto.response.CoachResponse;
import com.eventManager.weightlifting.model.Coach;
import org.springframework.stereotype.Component;

@Component
public class CoachMapper {
    public CoachResponse toResponse(Coach coach){
        return CoachResponse.builder()
                .id(coach.getId())
                .firstName(coach.getFirstName())
                .lastName(coach.getLastName())
                .dateOfBirth(coach.getDateOfBirth())
                .team(coach.getTeam())
                .build();
    }
}
