package com.eventManager.weightlifting.controller;

import com.eventManager.weightlifting.dto.event.EventWithJudgedAndCompetitorsResponse;
import com.eventManager.weightlifting.dto.request.EventRequest;
import com.eventManager.weightlifting.dto.response.EventResponse;
import com.eventManager.weightlifting.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAll(){
        List<EventResponse> res = service.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/full")
    public ResponseEntity<List<EventWithJudgedAndCompetitorsResponse>> getAllWithFullData(){
        List<EventWithJudgedAndCompetitorsResponse> res = service.getAllWithLists();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}/full")
    public ResponseEntity<EventWithJudgedAndCompetitorsResponse> getEventWithFullData(@PathVariable UUID id){
        return new ResponseEntity<>(service.getEventWithLists(id), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable UUID id){
        return new ResponseEntity<>(service.getEvent(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventResponse> addEvent(@RequestBody EventRequest body){
        EventResponse saved = service.upsertEvent(body, null);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<EventResponse> updateEvent(@RequestBody EventRequest body, @PathVariable UUID id){
        EventResponse saved = service.upsertEvent(body, id);
        return new ResponseEntity<>(saved, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id){
        service.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
