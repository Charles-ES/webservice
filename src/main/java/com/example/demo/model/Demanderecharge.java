package com.example.demo.model;

import java.sql.Date;

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
@Table(name = "demanderecharge")

public class Demanderecharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "idclient")
    long idclient;

    @Column(name = "idetat")
    Long idetat;

    @Column(name = "montant")
    double montant;

    @Column(name = "datedemanderecharge")
    Date datedemanderecharge;

}
