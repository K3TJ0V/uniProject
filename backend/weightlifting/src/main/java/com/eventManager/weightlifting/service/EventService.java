package com.eventManager.weightlifting.service;

import com.eventManager.weightlifting.dto.request.EventRequest;
import com.eventManager.weightlifting.dto.response.EventResponse;
import com.eventManager.weightlifting.mappers.EventMapper;
import com.eventManager.weightlifting.model.Competitor;
import com.eventManager.weightlifting.model.Event;
import com.eventManager.weightlifting.repo.EventRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepo eventRepo;
    private final EventMapper mapper;

    public EventResponse getEvent(UUID id){
        Event event = eventRepo.findById(id).orElseThrow();
        return mapper.toResponse(event);
    }

    public List<EventResponse> getAll() {
        List<Event> events = eventRepo.findAll();
        return mapper.toListResponse(events);
    }

    public EventResponse upsertEvent(EventRequest body, UUID id){

    }

    public void deleteEvent(UUID id){
        Optional<Event> event = eventRepo.findById(id);
        event.ifPresent(eventRepo::delete);
    }
}
