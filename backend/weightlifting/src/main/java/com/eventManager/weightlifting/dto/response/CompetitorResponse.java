package com.eventManager.weightlifting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitorResponse {
    private UUID id;
    private String firstName;
    private String lastname;
    private String weightCategory;
    private String ageCategory;
    private String gender;
    private LocalDate dateOfBirth;
}
