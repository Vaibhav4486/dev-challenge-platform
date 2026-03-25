package com.example.devChallenge.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devChallenge.dto.request.ChallengeRequestDTO;
import com.example.devChallenge.dto.request.SolutionRequestDTO;
import com.example.devChallenge.dto.response.ChallengeResponseDTO;
import com.example.devChallenge.dto.response.SolutionResponseDTO;
import com.example.devChallenge.entity.Challenge;
import com.example.devChallenge.service.ChallengeService;
import com.example.devChallenge.service.SolutionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/challenges")
public class ChallenegeController 
{
    private final ChallengeService challengeService;
    private final SolutionService solutionService;
    public ChallenegeController(ChallengeService challengeService, SolutionService solutionService) {
        this.challengeService = challengeService;
        this.solutionService = solutionService;
    }
    @GetMapping
public ResponseEntity<Page<ChallengeResponseDTO>> getAllChallenges(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String difficulty,
        @RequestParam(required = false) String tag) {
    return ResponseEntity.ok(challengeService.getAllChallenges(page, size, difficulty, tag));
}

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeResponseDTO> getChallengeById(@PathVariable Long id) {
        ChallengeResponseDTO challenge = challengeService.getChallengeById(id);
        return ResponseEntity.ok(challenge);
    }
    
        @PostMapping
     public ResponseEntity<ChallengeResponseDTO> createChallenge(@RequestBody ChallengeRequestDTO challengeRequestDTO) {
    ChallengeResponseDTO challenge = challengeService.createChallenge(challengeRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(challenge);
     
}
    
    @PutMapping("/{id}")
    public ResponseEntity<ChallengeResponseDTO> updateChallenge(@PathVariable Long id, @RequestBody ChallengeRequestDTO challengeRequestDTO) {
        ChallengeResponseDTO updatedChallenge = challengeService.updateChallenge(id, challengeRequestDTO);
        return ResponseEntity.ok(updatedChallenge);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallengeById(@PathVariable Long id)
    {
        challengeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
@GetMapping("/{id}/tags")    
public ResponseEntity<List<String>> getTagsForChallenge(@PathVariable Long id)
    {
        List<String> tags = challengeService.getTagsForChallenge(id);
        return ResponseEntity.ok(tags);
    }
    @GetMapping("/{id}/solutions")
    public ResponseEntity<List<SolutionResponseDTO>> getSolutionsForChallenge(@PathVariable Long id) {
        List<SolutionResponseDTO> solutions = solutionService.getSolutionForChallenge(id);
        return ResponseEntity.ok(solutions);
    }
    @PostMapping("/{id}/solutions")
    public ResponseEntity<SolutionResponseDTO> createSolution(@PathVariable Long id, @RequestBody SolutionRequestDTO solutionRequestDTO) {
        SolutionResponseDTO solution = solutionService.createSolution(id, solutionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(solution);
    }
    @GetMapping("/trending")
public ResponseEntity<List<ChallengeResponseDTO>> getTrendingChallenges() {
    return ResponseEntity.ok(challengeService.getTrendingChallenges());
}
}
