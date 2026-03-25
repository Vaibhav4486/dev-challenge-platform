package com.example.devChallenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devChallenge.dto.response.SolutionResponseDTO;
import com.example.devChallenge.service.SolutionService;
@RestController

@RequestMapping("/api/solutions")
public class SolutionController 
{
    private final SolutionService solutionService;
    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolutionResponseDTO> getSolutionById(@PathVariable Long id) {
        SolutionResponseDTO solution = solutionService.getSolutionById(id);
        return ResponseEntity.ok(solution);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolutionById(@PathVariable Long id)
    {
        solutionService.deleteSolutionById(id);
        return ResponseEntity.noContent().build();
    }
    
}
