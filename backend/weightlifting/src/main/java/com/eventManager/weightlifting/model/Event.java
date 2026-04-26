package com.eventManager.weightlifting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String rank;
    private String location;
    private LocalDate startDate;

    @ManyToMany
    @JoinTable(name = "event_competitors")
    private List<Competitor> competitors;
    @ManyToMany
    @JoinTable(name = "event_judges")
    private List<Judge> judges;
}
