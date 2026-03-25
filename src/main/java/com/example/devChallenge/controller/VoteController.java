package com.example.devChallenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devChallenge.dto.request.VoteRequestDTO;
import com.example.devChallenge.service.VoteService;

@RestController
@RequestMapping("/api/solutions")
public class VoteController 
{
    private final VoteService voteService;
    public VoteController(VoteService voteService) 
    {
        this.voteService = voteService;
    }
    
    @PostMapping("/{id}/vote")
    public ResponseEntity<Void> vote(@PathVariable Long id, @RequestBody VoteRequestDTO voteRequestDTO)
    {
        voteService.vote(id, voteRequestDTO);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/{id}/vote")
    public ResponseEntity<Void> removeVote(@PathVariable Long id)
    {
        voteService.removeVote(id);
        return ResponseEntity.noContent().build();
    }
}
