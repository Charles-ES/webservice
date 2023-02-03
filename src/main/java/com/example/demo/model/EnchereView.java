package com.example.demo.model;

import java.sql.Timestamp;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Immutable
@Table(name = "view_enchere")
public class EnchereView {

    @Id
    @Column(name = "id")
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

    @Column(name = "typecategorie")
    String typecategorie;

    @Column(name = "nom")
    String nom;
}
