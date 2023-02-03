package com.example.enchere.model;

import java.sql.Timestamp;

public class EnchereNoEntity {
    int idCategorie;
    String nom;
    Timestamp dateHeureDebut;
    Timestamp dateHeureFin;
    float prix;
    String photo;

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Timestamp getDateHeureDebut() {
        return dateHeureDebut;
    }

    public void setDateHeureDebut(Timestamp dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    public Timestamp getDateHeureFin() {
        return dateHeureFin;
    }

    public void setDateHeureFin(Timestamp dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
