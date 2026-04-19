package com.eventManager.weightlifting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JudgeResponse {
    private UUID id;
    private String firstName;
    private String lastname;
    private LocalDate dateOfBirth;
    private String licenseNumber;
}
