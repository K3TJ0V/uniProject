package com.eventManager.weightlifting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgeRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String licenseNumber;
}
