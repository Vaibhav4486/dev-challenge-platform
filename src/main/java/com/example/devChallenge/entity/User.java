// entity/User.java
package com.example.devChallenge.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import com.example.devChallenge.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private int points = 0;

    @CreationTimestamp
    private LocalDateTime createdAt;
}