package com.eventManager.weightlifting.mappers;

import com.eventManager.weightlifting.dto.coach.CoachForCompetitorDTO;
import com.eventManager.weightlifting.dto.competitor.CompetitorWithCoachResponse;
import com.eventManager.weightlifting.dto.response.CompetitorResponse;
import com.eventManager.weightlifting.model.Coach;
import com.eventManager.weightlifting.model.Competitor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompetitorMapper {
    public CompetitorResponse toResponse(Competitor comp){
        return CompetitorResponse.builder()
                .id(comp.getId())
                .firstName(comp.getFirstName())
                .lastName(comp.getLastName())
                .weightCategory(comp.getWeightCategory())
                .ageCategory(comp.getAgeCategory())
                .gender(comp.getGender())
                .dateOfBirth(comp.getDateOfBirth())
                .build();
    }
    public CompetitorWithCoachResponse toResponseWithCoachDTO(Competitor comp, Coach coach){
        CoachForCompetitorDTO coachDTO = CoachForCompetitorDTO.builder()
                .id(coach.getId())
                .firstName(coach.getFirstName())
                .lastName(coach.getLastName())
                .team(coach.getTeam())
                .build();

        return CompetitorWithCoachResponse.builder()
                .id(comp.getId())
                .firstName(comp.getFirstName())
                .lastName(comp.getLastName())
                .weightCategory(comp.getWeightCategory())
                .ageCategory(comp.getAgeCategory())
                .gender(comp.getGender())
                .dateOfBirth(comp.getDateOfBirth())
                .coach(coachDTO)
                .build();
    }

    public List<CompetitorResponse> toListResponse(List<Competitor> comps){
        return comps.stream()
                .map(this::toResponse)
                .toList();
    }

    public List<CompetitorWithCoachResponse> toListOfCompsWithCoachesDTO(List<Competitor> comps){
        return comps.stream()
                .map(competitor -> toResponseWithCoachDTO(competitor, competitor.getCoach()))
                .toList();
    }
}
