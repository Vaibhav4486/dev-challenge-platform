// entity/ChallengeTag.java
package com.example.devChallenge.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "challenge_tags")
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;
}