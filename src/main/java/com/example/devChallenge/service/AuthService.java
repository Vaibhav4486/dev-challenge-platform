package com.example.devChallenge.service;

import com.example.devChallenge.dto.request.LoginRequestDTO;
import com.example.devChallenge.dto.request.RegisterRequestDTO;
import com.example.devChallenge.dto.response.AuthResponseDTO;
import com.example.devChallenge.entity.User;
import com.example.devChallenge.enums.Role;
import com.example.devChallenge.repository.UserRepository;
import com.example.devChallenge.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER);
        user.setPoints(0);
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseDTO(token, user.getUsername(), user.getRole().name());
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseDTO(token, user.getUsername(), user.getRole().name());
    }
}