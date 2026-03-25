package com.example.devChallenge.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.example.devChallenge.enums.Difficulity;
import com.example.devChallenge.enums.Status;

import lombok.Data;

@Data
public class ChallengeResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Difficulity difficulty;
    private Status status;
    private String authorUsername;
    private LocalDateTime createdAt;
    private List<String> tags;
    private int solutionCount;
}