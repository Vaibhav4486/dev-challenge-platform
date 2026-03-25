package com.example.devChallenge.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devChallenge.dto.request.CommentRequestDTO;
import com.example.devChallenge.dto.response.CommentResponseDTO;
import com.example.devChallenge.service.CommentService;


@RestController
@RequestMapping("/api")
public class CommentController 
{
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping("/solutions/{id}/comments")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsBySolutionId(@PathVariable Long id)
    {
         List<CommentResponseDTO> comments=commentService.getCommentsBySolutionId(id);
         return ResponseEntity.ok(comments);
    }
    @PostMapping("/solutions/{id}/comments")
    public ResponseEntity<CommentResponseDTO> addComment(@PathVariable Long id, @RequestBody CommentRequestDTO dto)
    {
        CommentResponseDTO response = commentService.addComment(id, dto);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id)
    {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
