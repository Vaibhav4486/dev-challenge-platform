// entity/Challenge.java
package com.example.devChallenge.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import com.example.devChallenge.enums.Status;
import com.example.devChallenge.enums.Difficulity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "challenges")
@NoArgsConstructor
@AllArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulity difficulty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "challenge")
private List<ChallengeTag> challengeTags = new ArrayList<>();

@OneToMany(mappedBy = "challenge")
private List<Solution> solutions = new ArrayList<>();
}