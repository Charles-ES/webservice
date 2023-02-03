package com.example.enchere.repository;

import java.sql.Timestamp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.enchere.model.Enchere;

@Repository
public interface EnchereRepository extends CrudRepository<Enchere, Long>{

    Enchere findByIdClientAndNomProduitAndPrixDepartAndIdCategorieAndDateHeureDebutAndDateHeureFinAndDescription(Long idClient,
            String nomProduit, double prixDepart, Long idCategorie, Timestamp dateHeureDebut, Timestamp dateHeureFin, String description);
    
}
