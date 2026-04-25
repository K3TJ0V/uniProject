package com.eventManager.weightlifting.dto.competitor;

import com.eventManager.weightlifting.dto.coach.CoachForCompetitorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompetitorWithCoachResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String weightCategory;
    private String ageCategory;
    private String gender;
    private LocalDate dateOfBirth;
    private CoachForCompetitorDTO coach;
}
