package com.example.devChallenge.dto.response;

import java.time.LocalDateTime;

import com.example.devChallenge.entity.Solution;

import lombok.Data;

@Data
public class SolutionResponseDTO {
    private Long id;
    private String content;
    private String language;
    private int upvotes;
    private int downvotes;
    private String authorUsername;
    private Long challengeId;
    private LocalDateTime createdAt;
    
}