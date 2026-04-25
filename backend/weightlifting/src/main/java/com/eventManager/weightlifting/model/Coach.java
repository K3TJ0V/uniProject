package com.eventManager.weightlifting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coach")
public class Coach extends Person{
    private String team;

    @OneToMany(mappedBy = "coach", fetch = FetchType.LAZY)
    private List<Competitor> competitors;
}
