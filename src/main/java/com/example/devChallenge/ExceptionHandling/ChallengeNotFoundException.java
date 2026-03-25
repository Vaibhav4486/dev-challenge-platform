package com.example.devChallenge.ExceptionHandling;

public class ChallengeNotFoundException extends RuntimeException {
    public ChallengeNotFoundException(Long id) {
        super("Challenge not found with id: " + id);
    }
}
