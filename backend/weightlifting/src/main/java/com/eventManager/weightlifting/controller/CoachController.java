package com.eventManager.weightlifting.controller;


import com.eventManager.weightlifting.dto.request.CoachRequest;
import com.eventManager.weightlifting.dto.response.CoachResponse;
import com.eventManager.weightlifting.model.Coach;
import com.eventManager.weightlifting.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/coach")
public class CoachController {
    private final CoachService coachService;


    @GetMapping
    public ResponseEntity<List<CoachResponse>> getAll(){
        List<CoachResponse> allCoaches = coachService.getAll();
        return new ResponseEntity<>(allCoaches, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CoachResponse> getSingleCoach(@PathVariable UUID id){
        CoachResponse coach = coachService.getSingleCoach(id);
        if(coach == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(coach, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CoachResponse> addCoach(@RequestBody CoachRequest body){
        CoachResponse coach = coachService.upsertCoach(body, null);

        return new ResponseEntity<>(coach, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CoachResponse> updateCoach(@RequestBody CoachRequest body, @PathVariable UUID id){
        CoachResponse coach = coachService.upsertCoach(body, id);

        return new ResponseEntity<>(coach, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public void deleteCoach(@PathVariable UUID id){
        coachService.deleteCoach(id);
    }
}
