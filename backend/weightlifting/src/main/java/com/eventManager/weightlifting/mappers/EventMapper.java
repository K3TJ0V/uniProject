package com.eventManager.weightlifting.mappers;

import com.eventManager.weightlifting.dto.event.EventWithJudgedAndCompetitorsResponse;
import com.eventManager.weightlifting.dto.response.EventResponse;
import com.eventManager.weightlifting.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventMapper {
    private final JudgeMapper judgeMapper;
    private final CompetitorMapper competitorMapper;

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
    public List<EventWithJudgedAndCompetitorsResponse> EventWithJudgedAndCompetitorsMapper(Map<UUID, Event> events){
        return events.values().stream()
                .map(event -> EventWithJudgedAndCompetitorsResponse.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .rank(event.getRank())
                        .location(event.getLocation())
                        .startDate(event.getStartDate())
                        .judges(judgeMapper.toListResponse(event.getJudges()))
                        .competitors(competitorMapper.toListResponse(event.getCompetitors()))
                        .build())
                .toList();
    }
}
