package com.example.devChallenge.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SolutionRequestDTO 
{
    @NotBlank(message = "Content is required")
    private String content;
    @NotBlank(message = "Language is required")
    private String language;
    private Long authorId;
    private Long challengeId;


}
