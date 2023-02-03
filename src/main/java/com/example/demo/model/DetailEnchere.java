package com.example.demo.model;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Immutable
@Entity
@Table(name = "detail_enchere")
public class DetailEnchere {
    @Id
    @Column(name = "idenchere")
    Long idenchere;

    @Column(name = "nom")
    String nom;

    @Column(name = "prix")
    double prix;
}
