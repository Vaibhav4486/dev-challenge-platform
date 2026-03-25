package com.example.devChallenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devChallenge.entity.SolutionVote;

@Repository
public interface SolutionVoteRepository extends JpaRepository<SolutionVote, Long> {
    Optional<SolutionVote> findBySolutionIdAndVoterId(Long solutionId, Long voterId);
    boolean existsBySolutionIdAndVoterId(Long solutionId, Long voterId);
}