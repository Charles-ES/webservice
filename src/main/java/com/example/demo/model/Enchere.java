package com.example.demo.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enchere")
public class Enchere {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "idclient")
    Long idclient;

    @Column(name = "idcategorie")
    Long idcategorie;

    @Column(name = "nomproduit")
    String nomproduit;

    @Column(name = "dateheuredebut")
    Timestamp dateheuredebut;

    @Column(name = "dateheurefin")
    Timestamp dateheurefin;

    @Column(name = "prixdepart")
    double prixdepart;

    @Column(name = "idetat")
    Long idetat;

    
    @Column(name = "description")
    private String description;
}
