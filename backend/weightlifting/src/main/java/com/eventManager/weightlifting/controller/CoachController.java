package com.eventManager.weightlifting.controller;


import com.eventManager.weightlifting.dto.response.CoachResponse;
import com.eventManager.weightlifting.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
