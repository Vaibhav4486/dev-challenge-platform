package com.example.devChallenge.ExceptionHandling;

public class DuplicateVoteException extends RuntimeException {
    public DuplicateVoteException() {
        super("User has already voted for this solution");
    }
}
