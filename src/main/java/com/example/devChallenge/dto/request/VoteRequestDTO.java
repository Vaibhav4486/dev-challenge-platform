package com.example.devChallenge.dto.request;

import java.util.List;

import com.example.devChallenge.enums.VoteType;

public class VoteRequestDTO 
{
    private Long voterId;
    private VoteType voteType;
    public VoteType getVoteType() {
        return voteType;
    }
    
    
}
