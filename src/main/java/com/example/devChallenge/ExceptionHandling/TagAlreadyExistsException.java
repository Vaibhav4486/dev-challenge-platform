package com.example.devChallenge.ExceptionHandling;

public class TagAlreadyExistsException extends RuntimeException {
    public TagAlreadyExistsException(String name) {
        super("Tag already exists with name: " + name);
    }
    
}
