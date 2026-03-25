package com.example.devChallenge.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data

public class CommentRequestDTO 
{
    @NotBlank(message = "Content is required")
    private String content;
        private Long authorId;

    
}
