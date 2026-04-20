package com.eventManager.weightlifting.service;

import com.eventManager.weightlifting.dto.request.JudgeRequest;
import com.eventManager.weightlifting.dto.response.JudgeResponse;
import com.eventManager.weightlifting.model.Judge;
import com.eventManager.weightlifting.repo.JudgeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JudgeService {
    private final JudgeRepo judgeRepo;

    public List<JudgeResponse> getAll(){
        return judgeRepo.findAll().stream()
                .map(judge -> {
                    return JudgeResponse.builder()
                            .id(judge.getId())
                            .firstName(judge.getFirstName())
                            .lastName(judge.getLastName())
                            .licenseNumber(judge.getLicenseNumber())
                            .dateOfBirth(judge.getDateOfBirth())
                            .build();
                }).toList();
    }

    public JudgeResponse upsertJudge(JudgeRequest judgeData, UUID id){
        Judge judge = new Judge();
        if(id != null){
            judge = judgeRepo.findById(id).orElse(new Judge());
        }
        judge.setLicenseNumber(judgeData.getLicenseNumber());
        judge.setDateOfBirth(judgeData.getDateOfBirth());
        judge.setFirstName(judgeData.getFirstName());
        judge.setLastName(judgeData.getLastName());
        
        Judge savedEntity = judgeRepo.save(judge);

        return JudgeResponse.builder()
                .id(savedEntity.getId())
                .firstName(savedEntity.getFirstName())
                .lastName(savedEntity.getLastName())
                .licenseNumber(savedEntity.getLicenseNumber())
                .dateOfBirth(judge.getDateOfBirth())
                .build();
    }

    public JudgeResponse getById(UUID id){
        Judge judge = judgeRepo.findById(id)
                .orElse(null);

        if(judge != null){
            return JudgeResponse.builder()
                    .id(judge.getId())
                    .firstName(judge.getFirstName())
                    .lastName(judge.getLastName())
                    .licenseNumber(judge.getLicenseNumber())
                    .dateOfBirth(judge.getDateOfBirth())
                    .build();
        }else{
            return null;
        }
    }

    public void deleteById(UUID id){
        judgeRepo.findById(id).ifPresent(judgeRepo::delete);
    }
}
