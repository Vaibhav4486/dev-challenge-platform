package com.example.devChallenge.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devChallenge.dto.response.LeaderBoardResponseDTO;
import com.example.devChallenge.dto.response.UserStatsResponseDTO;
import com.example.devChallenge.service.LeaderBoardService;

@RestController
@RequestMapping("/api")
public class LeaderBoardController 
{
    private final LeaderBoardService leaderBoardService;

    public LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }
    @GetMapping("/leaderBoard")
    public ResponseEntity<List<LeaderBoardResponseDTO>> getUsers()
    {
        return ResponseEntity.ok(leaderBoardService.getTopUsers());
    }
    @GetMapping("/leaderBoard/weekly")
    public ResponseEntity<List<LeaderBoardResponseDTO>> getWeekTopUsers()
    {
        List<LeaderBoardResponseDTO> topUsers=leaderBoardService.getWeeklyTopUsers();
        return ResponseEntity.ok(topUsers);
    }
    @GetMapping("/users/{id}/stats")
    public ResponseEntity<UserStatsResponseDTO> getUserStats(@PathVariable Long id)
    {
        UserStatsResponseDTO stats=leaderBoardService.getUserStats(id);
        return ResponseEntity.ok(stats);
    }

}
