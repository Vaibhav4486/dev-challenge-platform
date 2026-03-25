package com.example.devChallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devChallenge.entity.Solution;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
    List<Solution> findByChallengeId(Long challengeId);
    List<Solution> findByAuthorId(Long authorId);
}
