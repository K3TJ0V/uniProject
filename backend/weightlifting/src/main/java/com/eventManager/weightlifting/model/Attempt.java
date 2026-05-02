package com.eventManager.weightlifting.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attempt", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"event_id", "competitor_id", "discipline", "attempt_number"})
})
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competitor_id", nullable = false)
    private Competitor competitor;

    @Enumerated(EnumType.STRING)
    private Discipline discipline;

    @Column(name = "attempt_number", nullable = false)
    private int attemptNumber;

    private Integer declaredWeight;

    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<JudgeDecision> judgeDecisions = new ArrayList<>();
}
