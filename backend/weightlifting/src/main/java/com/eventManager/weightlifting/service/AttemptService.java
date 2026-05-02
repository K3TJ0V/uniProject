package com.eventManager.weightlifting.service;

import com.eventManager.weightlifting.dto.request.AttemptRequest;
import com.eventManager.weightlifting.dto.response.AttemptResponse;
import com.eventManager.weightlifting.model.*;
import com.eventManager.weightlifting.repo.AttemptRepo;
import com.eventManager.weightlifting.repo.CompetitorRepo;
import com.eventManager.weightlifting.repo.EventRepo;
import com.eventManager.weightlifting.repo.JudgeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttemptService {

    private final AttemptRepo attemptRepo;
    private final EventRepo eventRepo;
    private final CompetitorRepo competitorRepo;
    private final JudgeRepo judgeRepo;

    @Transactional(readOnly = true)
    public List<AttemptResponse> getByEvent(UUID eventId) {
        return attemptRepo.findByEventIdWithDecisions(eventId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public AttemptResponse upsert(UUID eventId, AttemptRequest body) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        Competitor competitor = competitorRepo.findById(body.getCompetitorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Competitor not found"));

        Attempt attempt = attemptRepo
                .findByEvent_IdAndCompetitor_IdAndDisciplineAndAttemptNumber(
                        eventId, body.getCompetitorId(), body.getDiscipline(), body.getAttemptNumber())
                .orElseGet(() -> Attempt.builder()
                        .event(event)
                        .competitor(competitor)
                        .discipline(body.getDiscipline())
                        .attemptNumber(body.getAttemptNumber())
                        .build());

        attempt.setDeclaredWeight(body.getDeclaredWeight());
        attempt.getJudgeDecisions().clear();

        if (body.getDecisions() != null) {
            for (AttemptRequest.DecisionRequest d : body.getDecisions()) {
                Judge judge = judgeRepo.findById(d.getJudgeId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Judge not found"));
                attempt.getJudgeDecisions().add(JudgeDecision.builder()
                        .attempt(attempt)
                        .judge(judge)
                        .valid(d.isValid())
                        .build());
            }
        }

        return toResponse(attemptRepo.save(attempt));
    }

    private AttemptResponse toResponse(Attempt a) {
        List<AttemptResponse.DecisionResponse> decisions = a.getJudgeDecisions().stream()
                .map(d -> AttemptResponse.DecisionResponse.builder()
                        .judgeId(d.getJudge().getId())
                        .judgeName(d.getJudge().getFirstName() + " " + d.getJudge().getLastName())
                        .valid(d.isValid())
                        .build())
                .toList();

        AttemptResult result = computeResult(a.getJudgeDecisions());

        return AttemptResponse.builder()
                .id(a.getId())
                .competitorId(a.getCompetitor().getId())
                .competitorFirstName(a.getCompetitor().getFirstName())
                .competitorLastName(a.getCompetitor().getLastName())
                .discipline(a.getDiscipline())
                .attemptNumber(a.getAttemptNumber())
                .declaredWeight(a.getDeclaredWeight())
                .decisions(decisions)
                .result(result)
                .build();
    }

    private AttemptResult computeResult(List<JudgeDecision> decisions) {
        if (decisions.size() < 3) return AttemptResult.PENDING;
        long valid = decisions.stream().filter(JudgeDecision::isValid).count();
        return valid >= 2 ? AttemptResult.VALID : AttemptResult.NO_LIFT;
    }
}
