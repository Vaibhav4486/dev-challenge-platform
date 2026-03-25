package com.example.devChallenge.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.devChallenge.ExceptionHandling.UserNotFoundException;
import com.example.devChallenge.dto.response.LeaderBoardResponseDTO;
import com.example.devChallenge.dto.response.UserStatsResponseDTO;
import com.example.devChallenge.repository.CommentRepository;
import com.example.devChallenge.repository.SolutionRepository;
import com.example.devChallenge.repository.UserRepository;

@Service
public class LeaderBoardService {

    private final UserRepository userRepository;
    private final SolutionRepository solutionRepository;
    private final CommentRepository commentRepository;

    public LeaderBoardService(UserRepository userRepository, SolutionRepository solutionRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.solutionRepository = solutionRepository;
        this.commentRepository = commentRepository;
    }

    public List<LeaderBoardResponseDTO> getTopUsers() {
        AtomicInteger rank = new AtomicInteger(1);
        return userRepository.findAll().stream()
                .sorted((u1, u2) -> Integer.compare(u2.getPoints(), u1.getPoints()))
                .limit(10)
                .map(user -> {
                    LeaderBoardResponseDTO dto = new LeaderBoardResponseDTO();
                    dto.setRank(rank.getAndIncrement());
                    dto.setUsername(user.getUsername());
                    dto.setPoints(user.getPoints());
                    dto.setSolvedCount(solutionRepository.findByAuthorId(user.getId()).size());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<LeaderBoardResponseDTO> getWeeklyTopUsers() {
        return getTopUsers();
    }

    public UserStatsResponseDTO getUserStats(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        List<String> allUsersSorted = userRepository.findAll().stream()
                .sorted((u1, u2) -> Integer.compare(u2.getPoints(), u1.getPoints()))
                .map(u -> u.getUsername())
                .collect(Collectors.toList());

        int rank = allUsersSorted.indexOf(user.getUsername()) + 1;

        UserStatsResponseDTO dto = new UserStatsResponseDTO();
        dto.setUsername(user.getUsername());
        dto.setPoints(user.getPoints());
        dto.setTotalSolutions(solutionRepository.findByAuthorId(user.getId()).size());
        dto.setTotalComments(0);
        dto.setRank(rank);
        return dto;
    }
}