package com.eventManager.weightlifting.dto.response;

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
public class EventResponse {
    private UUID id;
    private String name;
    private String rank;
    private String location;
    private LocalDate startDate;
}
