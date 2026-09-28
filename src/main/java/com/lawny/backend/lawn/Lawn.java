package com.lawny.backend.lawn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Lawn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Lawn name is required")
    private String name;
    private String grassType;
    private Integer squareFeet;

    public Lawn() {
    }

    public Lawn(
            String name,
            String grassType,
            Integer squareFeet) {
        this.name = name;
        this.grassType = grassType;
        this.squareFeet = squareFeet;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrassType() {
        return grassType;
    }

    public void setGrassType(String grassType) {
        this.grassType = grassType;
    }

    public Integer getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(Integer squareFeet) {
        this.squareFeet = squareFeet;
    }
}
