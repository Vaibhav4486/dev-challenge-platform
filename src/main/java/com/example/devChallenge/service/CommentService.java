package com.example.devChallenge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.devChallenge.ExceptionHandling.SolutionNotFoundException;
import com.example.devChallenge.ExceptionHandling.UserNotFoundException;
import com.example.devChallenge.dto.request.CommentRequestDTO;
import com.example.devChallenge.dto.response.CommentResponseDTO;
import com.example.devChallenge.entity.Comment;
import com.example.devChallenge.repository.CommentRepository;
import com.example.devChallenge.repository.SolutionRepository;
import com.example.devChallenge.repository.UserRepository;

@Service
public class CommentService 
{
   private final CommentRepository commentRepository;
   private final UserRepository userRepository;
   private final SolutionRepository solutionRepository;
   public CommentService(CommentRepository commentRepository, UserRepository userRepository, SolutionRepository solutionRepository) {
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
    this.solutionRepository = solutionRepository;
}
  public List<CommentResponseDTO> getCommentsBySolutionId(Long solutionId)
  {
    List<Comment> comments = commentRepository.findBySolutionId(solutionId);
    return comments.stream().map(this::convertTODTO).toList();
  }
  public CommentResponseDTO addComment(Long solutionId,CommentRequestDTO request)
  {
     Comment comment = new Comment();
     comment.setContent(request.getContent());
comment.setAuthor(userRepository.findById(1L)
    .orElseThrow(() -> new UserNotFoundException(1L)));        comment.setSolution(solutionRepository.findById(solutionId).orElseThrow(() -> new SolutionNotFoundException(solutionId)));
        Comment savedComment = commentRepository.save(comment);
        return convertTODTO(savedComment);
  }    
   public void deleteComment(Long id)
   {
       if(!commentRepository.existsById(id))
       {
        throw new RuntimeException("Comment not found");
       }
       commentRepository.deleteById(id);

   } 
   public CommentResponseDTO convertTODTO(Comment c)
   {
      CommentResponseDTO dto = new CommentResponseDTO();
      dto.setId(c.getId());
      dto.setContent(c.getContent());
      dto.setAuthorUsername(c.getAuthor().getUsername());
      dto.setCreatedAt(c.getCreatedAt());
      return dto;
   }
}
