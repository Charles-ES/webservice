
package com.example.enchere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;


@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(name = "nom")
    String nom;
    
    @Column(name = "telephone")
    String telephone;
    
    @Column(name = "idgenre")
    Long idgenre;

    @Column(name = "motdepasse")
    String motdepasse;
    
}