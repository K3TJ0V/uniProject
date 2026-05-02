package com.eventManager.weightlifting.controller;

import com.eventManager.weightlifting.dto.request.JudgeRequest;
import com.eventManager.weightlifting.dto.response.JudgeResponse;
import com.eventManager.weightlifting.service.JudgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/judge")
public class JudgeController {
    private final JudgeService judgeService;

    @GetMapping
    public ResponseEntity<List<JudgeResponse>> getAll(){
        List<JudgeResponse> judgeList = judgeService.getAll();
        return new ResponseEntity<>(judgeList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<JudgeResponse> getById(@PathVariable UUID id){
        JudgeResponse res = judgeService.getById(id);
        if(res == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<JudgeResponse> insertJudge(@RequestBody JudgeRequest judge){
        JudgeResponse res = judgeService.upsertJudge(judge, null);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<JudgeResponse> updateJudge(@RequestBody JudgeRequest judge, @PathVariable UUID id){
        JudgeResponse res = judgeService.upsertJudge(judge, id);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJudge(@PathVariable UUID id){
        judgeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
