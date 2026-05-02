package com.eventManager.weightlifting.service;

import com.eventManager.weightlifting.dto.event.EventWithJudgedAndCompetitorsResponse;
import com.eventManager.weightlifting.dto.request.EventRequest;
import com.eventManager.weightlifting.dto.response.EventResponse;
import com.eventManager.weightlifting.mappers.EventMapper;
import com.eventManager.weightlifting.model.Competitor;
import com.eventManager.weightlifting.model.Event;
import com.eventManager.weightlifting.model.Judge;
import com.eventManager.weightlifting.repo.CompetitorRepo;
import com.eventManager.weightlifting.repo.EventRepo;
import com.eventManager.weightlifting.repo.JudgeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepo eventRepo;
    private final JudgeRepo judgeRepo;
    private final CompetitorRepo competitorRepo;
    private final EventMapper mapper;

    public EventResponse getEvent(UUID id){
        Event event = eventRepo.findById(id).orElseThrow();
        return mapper.toResponse(event);
    }

    public List<EventResponse> getAll() {
        List<Event> events = eventRepo.findAll();
        return mapper.toListResponse(events);
    }

    public EventWithJudgedAndCompetitorsResponse getEventWithLists(UUID id) {
        Event eventWithJudges = eventRepo.getAllWithJudges().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow();
        eventRepo.getAllWithCompetitors().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .ifPresent(e -> eventWithJudges.setCompetitors(e.getCompetitors()));
        return mapper.EventWithJudgedAndCompetitorsMapper(
                Map.of(eventWithJudges.getId(), eventWithJudges)).getFirst();
    }

    public List<EventWithJudgedAndCompetitorsResponse> getAllWithLists(){
        Map<UUID, Event> eventsWithJudges = eventRepo.getAllWithJudges().stream()
                .collect(Collectors.toMap(Event::getId, e -> e));
        eventRepo.getAllWithCompetitors()
                .forEach(event -> {
                    eventsWithJudges.get(event.getId()).setCompetitors(event.getCompetitors());
                });
        return mapper.EventWithJudgedAndCompetitorsMapper(eventsWithJudges);
    }

    public EventResponse upsertEvent(EventRequest body, UUID id){
        if (body.getJudges() == null || body.getJudges().size() < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event requires at least 3 judges");
        }

        Event event = new Event();
        if(id != null){
            event = eventRepo.findById(id).orElse(new Event());
        }
        if (body.getJudges() != null && !body.getJudges().isEmpty()) {
            List<Judge> judges = body.getJudges().stream()
                    .map(uuid -> judgeRepo.findById(uuid).orElseThrow())
                    .toList();
            event.setJudges(judges);
        }

        if (body.getCompetitors() != null && !body.getCompetitors().isEmpty()) {
            List<Competitor> competitors = body.getCompetitors().stream()
                    .map(uuid -> competitorRepo.findById(uuid).orElseThrow())
                    .toList();
            event.setCompetitors(competitors);
        }
        event.setName(body.getName());
        event.setRank(body.getRank());
        event.setStartDate(LocalDate.parse(body.getStartDate()));
        event.setLocation(body.getLocation());

        Event saved = eventRepo.save(event);

        return mapper.toResponse(saved);
    }

    public void deleteEvent(UUID id){
        Optional<Event> event = eventRepo.findById(id);
        event.ifPresent(eventRepo::delete);
    }
}
