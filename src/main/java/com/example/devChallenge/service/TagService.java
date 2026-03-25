package com.example.devChallenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.devChallenge.ExceptionHandling.ChallengeNotFoundException;
import com.example.devChallenge.ExceptionHandling.TagAlreadyExistsException;
import com.example.devChallenge.entity.Tag;
import com.example.devChallenge.repository.ChallenegeRespository;
import com.example.devChallenge.repository.TagRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class TagService 
{
    private final TagRepository tagRepository;
    private final ChallenegeRespository challengeRepository;
    public List<String> getAllTags()
    {
        return tagRepository.findAll().stream().map(tag -> tag.getName()).collect(Collectors.toList());
    }
    public List<String> getTagsForChallenge(Long challengeId)
    {
        return challengeRepository.findById(challengeId).orElseThrow(() -> new ChallengeNotFoundException(challengeId))
.getChallengeTags().stream()
    .map(ct -> ct.getTag().getName())
    .collect(Collectors.toList());    }
    public String createTag(String name)
    {
        if(tagRepository.findByName(name).isPresent())
        {
            throw new TagAlreadyExistsException(name);
        }
        Tag tag = new Tag();
tag.setName(name);
return tagRepository.save(tag).getName();
    }
}
