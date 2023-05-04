package com.example.patientsmvc.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Patient {
    @Id @GeneratedValue
    private Long id;
    private String nom;
    @Temporal(value = TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;
    private int score;
}
