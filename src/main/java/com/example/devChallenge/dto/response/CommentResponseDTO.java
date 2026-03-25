package com.example.devChallenge.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentResponseDTO {
    private Long id;
    private String content;
    private String authorUsername;
    private LocalDateTime createdAt;
}
