package com.example.enchere.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "enchere")

public class Enchere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pour l'incrémentation
    @Column(name = "id")
    private Long id;

    @Column(name = "idclient")
    private Long idClient;

    @Column(name = "idcategorie")
    private Long idCategorie;

    @Column(name = "nomproduit")
    private String nomProduit;

    @Column(name = "dateheuredebut")
    private Timestamp dateHeureDebut;

    @Column(name = "dateheurefin")
    private Timestamp dateHeureFin;

    @Column(name = "prixdepart")
    private double prixDepart;

    @Column(name = "description")
    private String description;
}
