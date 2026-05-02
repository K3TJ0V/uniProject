package com.eventManager.weightlifting.dto.event;

import com.eventManager.weightlifting.dto.response.CompetitorResponse;
import com.eventManager.weightlifting.dto.response.JudgeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventWithJudgedAndCompetitorsResponse {
    private UUID id;
    private String name;
    private String rank;
    private String location;
    private LocalDate startDate;
    private List<JudgeResponse> judges;
    private List<CompetitorResponse> competitors;
}
