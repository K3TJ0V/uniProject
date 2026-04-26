package com.eventManager.weightlifting.dto.judge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgeForEventDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String licenseNumber;
}
