package com.example.devChallenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.devChallenge.ExceptionHandling.ChallengeNotFoundException;
import com.example.devChallenge.ExceptionHandling.UserNotFoundException;
import com.example.devChallenge.dto.request.ChallengeRequestDTO;
import com.example.devChallenge.dto.response.ChallengeResponseDTO;
import com.example.devChallenge.entity.Challenge;
import com.example.devChallenge.entity.User;
import com.example.devChallenge.enums.Difficulity;
import com.example.devChallenge.repository.ChallenegeRespository;
import com.example.devChallenge.repository.UserRepository;

@Service
public class ChallengeService {

    private final ChallenegeRespository challenegeRespository;
    private final UserRepository userRepository;


    public ChallengeService(ChallenegeRespository challenegeRespository, UserRepository userRepository) {
        this.challenegeRespository = challenegeRespository;
        this.userRepository = userRepository;
    }

    public Page<ChallengeResponseDTO> getAllChallenges(int page, int size, String difficulty, String tag) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Challenge> challenges;

    if (difficulty != null) {
        challenges = challenegeRespository.findByDifficulty(Difficulity.valueOf(difficulty.toUpperCase()), pageable);
    } else {
        challenges = challenegeRespository.findAll(pageable);
    }

    return challenges.map(this::convertToDTO);
}
public List<ChallengeResponseDTO> getTrendingChallenges() {
    return challenegeRespository.findAll().stream()
            .sorted((c1, c2) -> Integer.compare(c2.getSolutions().size(), c1.getSolutions().size()))
            .limit(10)
            .map(this::convertToDTO)
            .collect(Collectors.toList());
}

    public ChallengeResponseDTO getChallengeById(Long id) {
        return challenegeRespository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ChallengeNotFoundException(id));

    }
    public ChallengeResponseDTO createChallenge(ChallengeRequestDTO dto) 
    {
        
        Challenge challenge = new Challenge();
    challenge.setTitle(dto.getTitle());
    challenge.setDescription(dto.getDescription());
    challenge.setDifficulty(dto.getDifficulty());
    challenge.setStatus(dto.getStatus());
    User author = userRepository.findById(1L)
    .orElseThrow(() -> new UserNotFoundException(1L));
challenge.setAuthor(author);
    Challenge saved = challenegeRespository.save(challenge);
    return convertToDTO(saved);
    }
    public ChallengeResponseDTO updateChallenge(Long id, ChallengeRequestDTO dto) {
    Challenge challenge = challenegeRespository.findById(id)
            .orElseThrow(() -> new ChallengeNotFoundException(id));
    challenge.setTitle(dto.getTitle());
    challenge.setDescription(dto.getDescription());
    challenge.setDifficulty(dto.getDifficulty());
    challenge.setStatus(dto.getStatus());
    
    return convertToDTO(challenegeRespository.save(challenge));
}
    public void deleteById(Long id )
    {
        if(!challenegeRespository.existsById(id))
        {
            throw new ChallengeNotFoundException(id);
        }
        challenegeRespository.deleteById(id);

    }
    public List<String> getTagsForChallenge(Long challengeId)
    {
        return challenegeRespository.findById(challengeId).orElseThrow(() -> new ChallengeNotFoundException(challengeId))
.getChallengeTags().stream()
    .map(ct -> ct.getTag().getName())
    .collect(Collectors.toList());    }

    private ChallengeResponseDTO convertToDTO(Challenge challenge) {
        ChallengeResponseDTO dto = new ChallengeResponseDTO();
        dto.setId(challenge.getId());
        dto.setTitle(challenge.getTitle());
        dto.setDescription(challenge.getDescription());
        dto.setDifficulty(challenge.getDifficulty());
        dto.setStatus(challenge.getStatus());
        dto.setAuthorUsername(challenge.getAuthor().getUsername());
        dto.setCreatedAt(challenge.getCreatedAt());
        dto.setTags(challenge.getChallengeTags().stream()
                .map(ct -> ct.getTag().getName())
                .collect(Collectors.toList()));
        dto.setSolutionCount(challenge.getSolutions().size());
        return dto;
    }
}