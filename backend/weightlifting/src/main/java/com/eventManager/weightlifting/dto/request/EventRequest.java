package com.eventManager.weightlifting.dto.request;

import com.eventManager.weightlifting.model.Competitor;
import com.eventManager.weightlifting.model.Judge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventRequest {
    private String name;
    private String rank;
    private String location;
    private String startDate;
    private List<UUID> judges;
    private List<UUID> competitors;
}

