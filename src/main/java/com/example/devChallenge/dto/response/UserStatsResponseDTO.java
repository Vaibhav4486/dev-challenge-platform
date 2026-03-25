package com.example.devChallenge.dto.response;

import lombok.Data;

@Data
public class UserStatsResponseDTO 
{
    private String username;
    private int points;
    private int totalSolutions;
    private int totalComments;
    private int rank;
}
