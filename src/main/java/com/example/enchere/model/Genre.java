package com.example.enchere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "libelle")
    String libelle;
}
