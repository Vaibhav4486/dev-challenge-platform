package com.example.devChallenge.dto.response;

import java.util.Optional;

import com.example.devChallenge.entity.Comment;

import java.util.Optional;

import com.example.devChallenge.entity.Comment;

import lombok.Data;

@Data
public class LeaderBoardResponseDTO {
    private int rank;
    private String username;
    private int points;
    private int solvedCount;
}