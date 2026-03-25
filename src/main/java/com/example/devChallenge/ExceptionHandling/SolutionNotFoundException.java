package com.example.devChallenge.ExceptionHandling;

public class SolutionNotFoundException extends RuntimeException {
    public SolutionNotFoundException(Long id) {
        super("Solution not found with id: " + id);
    }
}
