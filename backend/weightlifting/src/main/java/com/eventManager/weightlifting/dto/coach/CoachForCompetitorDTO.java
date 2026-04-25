package com.eventManager.weightlifting.dto.coach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoachForCompetitorDTO {
    private UUID id;
    private String firstName;
    private String lastName;
}
