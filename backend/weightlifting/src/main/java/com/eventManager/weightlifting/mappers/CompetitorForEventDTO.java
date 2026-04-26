package com.eventManager.weightlifting.mappers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitorForEventDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String ageCategory;
    private String weightCategory;
    private String gender;
}
