package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Enchere;

public interface EnchereRepository extends JpaRepository<Enchere,Long>{

    List<Enchere> findByIdetat(Long idetat);
    List<Enchere> findByIdclient(Long idclient);
    Enchere findByIdClientAndNomProduitAndPrixDepartAndIdCategorieAndDateHeureDebutAndDateHeureFinAndDescription(
            Long idclient, String nomproduit, double prixdepart, Long idcategorie, Timestamp dateheuredebut,
            Timestamp dateheurefin, String description);
}
