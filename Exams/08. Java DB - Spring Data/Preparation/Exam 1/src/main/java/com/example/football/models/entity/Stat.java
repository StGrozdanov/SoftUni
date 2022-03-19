package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class Stat extends BaseEntity {
    private Double shooting;
    private Double passing;
    private Double endurance;

    @Column(nullable = false, columnDefinition = "FLOAT")
    public Double getShooting() {
        return shooting;
    }

    public void setShooting(Double shooting) {
        this.shooting = shooting;
    }

    @Column(nullable = false, columnDefinition = "FLOAT")
    public Double getPassing() {
        return passing;
    }

    public void setPassing(Double passing) {
        this.passing = passing;
    }

    @Column(nullable = false, columnDefinition = "FLOAT")
    public Double getEndurance() {
        return endurance;
    }

    public void setEndurance(Double endurance) {
        this.endurance = endurance;
    }
}
