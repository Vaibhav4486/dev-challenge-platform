package com.example.devChallenge.ExceptionHandling;

public class UserNotFoundException 
extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
    
}
