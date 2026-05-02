package com.eventManager.weightlifting.dto.response;

import com.eventManager.weightlifting.model.AttemptResult;
import com.eventManager.weightlifting.model.Discipline;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttemptResponse {
    private UUID id;
    private UUID competitorId;
    private String competitorFirstName;
    private String competitorLastName;
    private Discipline discipline;
    private int attemptNumber;
    private Integer declaredWeight;
    private List<DecisionResponse> decisions;
    private AttemptResult result;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DecisionResponse {
        private UUID judgeId;
        private String judgeName;
        private boolean valid;
    }
}
