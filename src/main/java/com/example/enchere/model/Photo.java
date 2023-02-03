package com.example.enchere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pour l'incrémentation
    @Column(name = "id")
    private Long id;

    @Column(name = "base64image")
    private String base64image;

    @Column(name = "idenchere")
    private Long idenchere;
}
