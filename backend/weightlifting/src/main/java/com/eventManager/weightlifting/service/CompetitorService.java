package com.eventManager.weightlifting.service;

import com.eventManager.weightlifting.model.Competitor;
import com.eventManager.weightlifting.repo.CompetitorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitorService {
    private final CompetitorRepo competitorRepo;

    public List<Competitor> getAll(){
        return competitorRepo.findAll();
    }
}
