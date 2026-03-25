package com.example.devChallenge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devChallenge.entity.Tag;
import com.example.devChallenge.service.TagService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/tags")
public class TagController 
{
    private final TagService tagService;
    public TagController(TagService tagService)
    {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllTags()
    {
        // Call the service to get all tags
        List<String> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }
    @PostMapping
    public ResponseEntity<String> createTag(@RequestParam String name)
    {
        String createdTagName = tagService.createTag(name);
        return ResponseEntity.ok(createdTagName);
    }
    
    

    
    
}
