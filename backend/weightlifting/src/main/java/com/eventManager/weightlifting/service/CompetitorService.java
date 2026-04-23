package com.eventManager.weightlifting.service;

import com.eventManager.weightlifting.dto.request.CompetitorRequest;
import com.eventManager.weightlifting.dto.response.CompetitorResponse;
import com.eventManager.weightlifting.model.Competitor;
import com.eventManager.weightlifting.repo.CompetitorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompetitorService{
    private final CompetitorRepo competitorRepo;

    public List<CompetitorResponse> getAll(){
        return competitorRepo.findAll().stream()
                .map(competitor -> {
                    return CompetitorResponse.builder()
                            .id(competitor.getId())
                            .firstName(competitor.getFirstName())
                            .lastName(competitor.getLastName())
                            .weightCategory(competitor.getWeightCategory())
                            .ageCategory(competitor.getAgeCategory())
                            .gender(competitor.getGender())
                            .dateOfBirth(competitor.getDateOfBirth())
                            .build();
                })
                .toList();
    }

    public CompetitorResponse getSingle(UUID id) {
        CompetitorResponse competitor = null;

        Competitor fetchedCompetitor = competitorRepo.findById(id).orElse(null);
        if(fetchedCompetitor != null){
            competitor = CompetitorResponse.builder()
                    .id(fetchedCompetitor.getId())
                    .firstName(fetchedCompetitor.getFirstName())
                    .lastName(fetchedCompetitor.getLastName())
                    .weightCategory(fetchedCompetitor.getWeightCategory())
                    .ageCategory(fetchedCompetitor.getAgeCategory())
                    .gender(fetchedCompetitor.getGender())
                    .dateOfBirth(fetchedCompetitor.getDateOfBirth())
                    .build();
        }

        return competitor;
    }

    public CompetitorResponse upsertCompetitor(CompetitorRequest competitor, UUID id) {
        Competitor comp = new Competitor();
        if(id != null){
            comp = competitorRepo.findById(id).orElse(new Competitor());
        }
        comp.setAgeCategory(competitor.getAgeCategory());
        comp.setWeightCategory(competitor.getWeightCategory());
        comp.setGender(competitor.getGender());
        comp.setFirstName(competitor.getFirstName());
        comp.setLastName(competitor.getLastName());
        comp.setDateOfBirth(competitor.getDateOfBirth());

        Competitor saved = competitorRepo.save(comp);

        return CompetitorResponse.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .weightCategory(saved.getWeightCategory())
                .ageCategory(saved.getAgeCategory())
                .gender(saved.getGender())
                .dateOfBirth(saved.getDateOfBirth())
                .build();
    }

    public void deleteById(UUID id){
        competitorRepo.findById(id).ifPresent(competitorRepo::delete);
    }
}
