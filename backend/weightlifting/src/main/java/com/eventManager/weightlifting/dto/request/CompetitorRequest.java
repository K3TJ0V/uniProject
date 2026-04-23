package com.eventManager.weightlifting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitorRequest {
    private String firstName;
    private String lastName;
    private String weightCategory;
    private String ageCategory;
    private String gender;
    private LocalDate dateOfBirth;
}
