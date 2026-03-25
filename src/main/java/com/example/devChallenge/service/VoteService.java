package com.example.devChallenge.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.devChallenge.ExceptionHandling.DuplicateVoteException;
import com.example.devChallenge.ExceptionHandling.SolutionNotFoundException;
import com.example.devChallenge.ExceptionHandling.UserNotFoundException;
import com.example.devChallenge.dto.request.VoteRequestDTO;
import com.example.devChallenge.entity.Solution;
import com.example.devChallenge.entity.SolutionVote;
import com.example.devChallenge.entity.User;
import com.example.devChallenge.enums.VoteType;
import com.example.devChallenge.repository.SolutionRepository;
import com.example.devChallenge.repository.SolutionVoteRepository;
import com.example.devChallenge.repository.UserRepository;

@Service
public class VoteService
{
    private final SolutionRepository solutionRepository;
    private final SolutionVoteRepository solutionVoteRepository;
    private final UserRepository userRepository;
    public VoteService(SolutionRepository solutionRepository, SolutionVoteRepository solutionVoteRepository, UserRepository userRepository) {
        this.solutionRepository = solutionRepository;
        this.solutionVoteRepository = solutionVoteRepository;
        this.userRepository = userRepository;
    }
    public void vote(Long solutionId ,VoteRequestDTO voteRequestDTO)
    {
        if(solutionVoteRepository.existsBySolutionIdAndVoterId(solutionId,1L))
        {
            throw new DuplicateVoteException();
        }
        Solution s=solutionRepository.findById(solutionId).orElseThrow(()->new SolutionNotFoundException(solutionId));
        User voter = userRepository.findById(1L)
    .orElseThrow(() -> new UserNotFoundException(1L));
    SolutionVote vote = new SolutionVote();
    vote.setSolution(s);
    vote.setVoter(voter);
    vote.setVoteType(voteRequestDTO.getVoteType());
    solutionVoteRepository.save(vote);
        User author = s.getAuthor();

    if(voteRequestDTO.getVoteType()==VoteType.UPVOTE)
    {
        s.setUpvotes(s.getUpvotes()+1);
        author.setPoints(author.getPoints() + 10); 

    }
    else if(voteRequestDTO.getVoteType()==VoteType.DOWNVOTE)
    {
        s.setDownvotes(s.getDownvotes()+1);
        author.setPoints(author.getPoints() - 5); 

    }
    solutionRepository.save(s);
userRepository.save(author);
}
public void removeVote(Long solutionId)
{
    SolutionVote vote = solutionVoteRepository.findBySolutionIdAndVoterId(1L, solutionId)
            .orElseThrow(() -> new RuntimeException("Vote not found for this solution and user"));
    Solution s = vote.getSolution();
        User author = s.getAuthor();

    if(vote.getVoteType()==VoteType.UPVOTE)
    {
        s.setUpvotes(s.getUpvotes()-1);
            author.setPoints(author.getPoints() - 10); // or +5 for downvote

    }
    else if(vote.getVoteType()==VoteType.DOWNVOTE)
    {
        s.setDownvotes(s.getDownvotes()-1);
            author.setPoints(author.getPoints() + 5); // or -10 for upvote

    }
    solutionRepository.save(s);
    userRepository.save(author);
        solutionVoteRepository.delete(vote);

}
    
}
