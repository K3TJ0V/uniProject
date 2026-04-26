package com.eventManager.weightlifting.controller;

import com.eventManager.weightlifting.dto.competitor.CompetitorWithCoachResponse;
import com.eventManager.weightlifting.dto.request.CompetitorRequest;
import com.eventManager.weightlifting.dto.response.CompetitorResponse;
import com.eventManager.weightlifting.service.CompetitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/competitor")
public class CompetitorController {
    private final CompetitorService compService;

    @GetMapping
    public ResponseEntity<List<CompetitorResponse>> getAllCompetitors(){
        List<CompetitorResponse> res = compService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitorResponse> getSingleCompetitor(@PathVariable UUID id){
        CompetitorResponse res = compService.getSingle(id);
        if(res == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}/coach")
    public ResponseEntity<CompetitorWithCoachResponse> getCompetitorWithCoach(@PathVariable UUID id){
        CompetitorWithCoachResponse res = compService.getSingleWithCoach(id);
        if(res == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/with-coaches")
    public ResponseEntity<List<CompetitorWithCoachResponse>> getAllCompetitorsWithCoaches(){
        List<CompetitorWithCoachResponse> res = compService.getAllWithCoaches();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompetitorResponse> addCompetitor(@RequestBody CompetitorRequest competitor){
        CompetitorResponse res = compService.upsertCompetitor(competitor, null);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitorResponse> updateCompetitor(@RequestBody CompetitorRequest competitor, @PathVariable UUID id){
        CompetitorResponse res = compService.upsertCompetitor(competitor, id);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetitor(@PathVariable UUID id){
        compService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
