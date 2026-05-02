package com.eventManager.weightlifting.dto.request;

import com.eventManager.weightlifting.model.Discipline;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttemptRequest {
    private UUID competitorId;
    private Discipline discipline;
    private int attemptNumber;
    private Integer declaredWeight;
    private List<DecisionRequest> decisions;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DecisionRequest {
        private UUID judgeId;
        private boolean valid;
    }
}
