package com.eventManager.weightlifting.mappers;

import com.eventManager.weightlifting.dto.response.JudgeResponse;
import com.eventManager.weightlifting.model.Judge;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JudgeMapper {
    public JudgeResponse toResponse(Judge judge){
        return JudgeResponse.builder()
                .id(judge.getId())
                .firstName(judge.getFirstName())
                .lastName(judge.getLastName())
                .licenseNumber(judge.getLicenseNumber())
                .dateOfBirth(judge.getDateOfBirth())
                .build();
    }
    public List<JudgeResponse> toListResponse(List<Judge> judges){
        return judges.stream()
                .map(this::toResponse)
                .toList();
    }
}
