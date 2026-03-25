package com.example.devChallenge.dto.request;

import java.util.List;

import com.example.devChallenge.enums.Difficulity;
import com.example.devChallenge.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChallengeRequestDTO 
{
    @NotBlank(message = "Title is mandatory")
    private String title;
     @NotBlank(message = "description is mandatory")

    private String description;
    private Difficulity difficulty;
    private Status status;
    List<Long> tagIds;
}
