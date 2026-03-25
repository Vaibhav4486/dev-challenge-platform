// entity/SolutionVote.java
package com.example.devChallenge.entity;

import com.example.devChallenge.enums.VoteType;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(
    name = "solution_votes",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"solution_id", "voter_id"}
        // one vote per user per solution
    )
)
@NoArgsConstructor
@AllArgsConstructor
public class SolutionVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solution_id", nullable = false)
    private Solution solution;

    @ManyToOne
    @JoinColumn(name = "voter_id", nullable = false)
    private User voter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoteType voteType;
}