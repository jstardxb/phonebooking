package com.jstar.phone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String model;
    private LocalDateTime bookedAt;
    private String bookedBy;

    @JsonIgnore
    public boolean isAvailable() {
        return bookedAt == null && bookedBy == null;
    }

    public String getAvailability() {
        return isAvailable() ? "Yes" : "No";
    }
}