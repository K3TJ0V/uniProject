package com.eventManager.weightlifting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "competitor")
public class Competitor extends Person{
    private String ageCategory;
    private String weightCategory;
    @Column(nullable = false)
    private String gender;
}
