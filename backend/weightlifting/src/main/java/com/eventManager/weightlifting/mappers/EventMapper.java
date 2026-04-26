package com.eventManager.weightlifting.mappers;

import com.eventManager.weightlifting.dto.response.EventResponse;
import com.eventManager.weightlifting.model.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventMapper {
    public EventResponse toResponse(Event event){
        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .rank(event.getRank())
                .location(event.getLocation())
                .startDate(event.getStartDate())
                .build();
    }
    public List<EventResponse> toListResponse(List<Event> events){
        return events.stream()
                .map(this::toResponse).toList();
    }
}
