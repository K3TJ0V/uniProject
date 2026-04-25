package com.eventManager.weightlifting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "judge")
public class Judge extends Person {
    private String licenseNumber;

    @ManyToMany(mappedBy = "judges")
    private List<Event> events;
}
