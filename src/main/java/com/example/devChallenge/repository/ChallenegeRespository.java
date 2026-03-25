package com.example.devChallenge.repository;

import com.example.devChallenge.entity.Challenge;
import com.example.devChallenge.enums.Difficulity;
import com.example.devChallenge.enums.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ChallenegeRespository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByStatus(Status status);
    List<Challenge> findByDifficulty(Difficulity difficulity);
    Page<Challenge> findByDifficulty(Difficulity difficulty, Pageable pageable);
Page<Challenge> findAll(Pageable pageable);

    
}
