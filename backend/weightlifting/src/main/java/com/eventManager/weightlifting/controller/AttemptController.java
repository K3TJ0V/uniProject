package com.eventManager.weightlifting.controller;

import com.eventManager.weightlifting.dto.request.AttemptRequest;
import com.eventManager.weightlifting.dto.response.AttemptResponse;
import com.eventManager.weightlifting.service.AttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event/{eventId}/attempts")
@RequiredArgsConstructor
public class AttemptController {

    private final AttemptService attemptService;

    @GetMapping
    public ResponseEntity<List<AttemptResponse>> getByEvent(@PathVariable UUID eventId) {
        return ResponseEntity.ok(attemptService.getByEvent(eventId));
    }

    @PostMapping
    public ResponseEntity<AttemptResponse> upsert(
            @PathVariable UUID eventId,
            @RequestBody AttemptRequest body) {
        return new ResponseEntity<>(attemptService.upsert(eventId, body), HttpStatus.OK);
    }
}
