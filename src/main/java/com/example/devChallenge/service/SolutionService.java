package com.example.devChallenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.devChallenge.ExceptionHandling.ChallengeNotFoundException;
import com.example.devChallenge.ExceptionHandling.SolutionNotFoundException;
import com.example.devChallenge.ExceptionHandling.UserNotFoundException;
import com.example.devChallenge.dto.request.SolutionRequestDTO;
import com.example.devChallenge.dto.response.SolutionResponseDTO;
import com.example.devChallenge.entity.Solution;
import com.example.devChallenge.entity.User;
import com.example.devChallenge.repository.ChallenegeRespository;
import com.example.devChallenge.repository.SolutionRepository;
import com.example.devChallenge.repository.UserRepository;

@Service
public class SolutionService 
{
    private final ChallenegeRespository challenegeRespository;
    private final UserRepository userRepository;
    private final SolutionRepository solutionRepository;
    public SolutionService(ChallenegeRespository challenegeRespository, UserRepository userRepository, SolutionRepository solutionRepository) {
        this.challenegeRespository = challenegeRespository;
        this.userRepository = userRepository;
        this.solutionRepository = solutionRepository;
    }
    public List<SolutionResponseDTO> getSolutionForChallenge(Long challengeId) {
    return solutionRepository.findByChallengeId(challengeId)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
}
    public SolutionResponseDTO getSolutionById(Long id)
    {
        Solution s=solutionRepository.findById(id).orElseThrow(() -> new SolutionNotFoundException(id));
    
        return convertToDTO(s);}
    public SolutionResponseDTO createSolution(Long challengeId, SolutionRequestDTO dto)
    {
        Solution solution = new Solution();
        solution.setContent(dto.getContent());
        solution.setLanguage(dto.getLanguage());
        solution.setChallenge(challenegeRespository.findById(challengeId).orElseThrow(() -> new ChallengeNotFoundException(challengeId)));
        User author = userRepository.findById(1L).orElseThrow(() -> new UserNotFoundException(1L));
        solution.setAuthor(author);
        solutionRepository.save(solution);
        return convertToDTO(solution);
    }
    public void deleteSolutionById(Long id)
    {
        if(!solutionRepository.existsById(id))
        {
            throw new SolutionNotFoundException(id);
        }
        solutionRepository.deleteById(id);
    }

   private SolutionResponseDTO convertToDTO(Solution solution) {
    SolutionResponseDTO dto = new SolutionResponseDTO();
    dto.setId(solution.getId());
    dto.setContent(solution.getContent());
    dto.setLanguage(solution.getLanguage());
    dto.setUpvotes(solution.getUpvotes());
    dto.setDownvotes(solution.getDownvotes());
    dto.setAuthorUsername(solution.getAuthor().getUsername());
    dto.setChallengeId(solution.getChallenge().getId());
    dto.setCreatedAt(solution.getCreatedAt());
    return dto;
}
    
    
}
