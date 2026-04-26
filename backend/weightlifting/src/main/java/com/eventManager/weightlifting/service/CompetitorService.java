package com.eventManager.weightlifting.service;

import com.eventManager.weightlifting.dto.competitor.CompetitorWithCoachResponse;
import com.eventManager.weightlifting.dto.request.CompetitorRequest;
import com.eventManager.weightlifting.dto.response.CompetitorResponse;
import com.eventManager.weightlifting.mappers.CompetitorMapper;
import com.eventManager.weightlifting.model.Coach;
import com.eventManager.weightlifting.model.Competitor;
import com.eventManager.weightlifting.repo.CoachRepo;
import com.eventManager.weightlifting.repo.CompetitorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompetitorService{
    private final CompetitorRepo competitorRepo;
    private final CoachRepo coachRepo;
    private final CompetitorMapper mapper;

    public List<CompetitorResponse> getAll(){
        return competitorRepo.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<CompetitorWithCoachResponse> getAllWithCoaches() {
        List<Competitor> comps = competitorRepo.getAllCompetitorsWithCoaches();
        return mapper.toListOfCompsWithCoachesDTO(comps);
    }

    public CompetitorResponse getSingle(UUID id) {
        CompetitorResponse competitor = null;

        Competitor fetchedCompetitor = competitorRepo.findById(id).orElse(null);
        if(fetchedCompetitor != null){
            competitor = mapper.toResponse(fetchedCompetitor);
        }

        return competitor;
    }

    public CompetitorWithCoachResponse getSingleWithCoach(UUID id) {
        Competitor comp = competitorRepo.getCompetitorWithCoach(id).orElseThrow();
        Coach coach = comp.getCoach();
        return mapper.toResponseWithCoachDTO(comp, coach);
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
        if(competitor.getCoachId() != null) {
            Coach coach = coachRepo.getReferenceById(competitor.getCoachId());
            comp.setCoach(coach);
        }
        Competitor saved = competitorRepo.save(comp);

        return mapper.toResponse(saved);
    }

    public void deleteById(UUID id){
        competitorRepo.findById(id).ifPresent(competitorRepo::delete);
    }
}
