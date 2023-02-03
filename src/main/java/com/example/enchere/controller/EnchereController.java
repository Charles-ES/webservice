package com.example.enchere.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.enchere.response.Error;
import com.example.enchere.response.Succes;
import com.example.enchere.model.Enchere;
import com.example.enchere.model.Photo;
import com.example.enchere.model.Token;
import com.example.enchere.repository.EnchereRepository;
import com.example.enchere.repository.PhotoRepository;
import com.example.enchere.repository.TokenRepository;
import com.example.enchere.repository.V_EnchereRepository;
import com.example.enchere.service.EnchereService;

@CrossOrigin("*")
@RestController
public class EnchereController {
    @Autowired
    private EnchereRepository enchereRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private V_EnchereRepository v_enchereRepository;


    @GetMapping("/encheres")
    public ResponseEntity listerEnchere(){
        ResponseEntity responseentity = null;
        Succes succes = new Succes();
        succes.setData(v_enchereRepository.findAll());
        responseentity = new ResponseEntity(succes, HttpStatus.OK);
        return responseentity;
    }

    @PostMapping("/enchere/ajouter")
    public ResponseEntity ajouterEnchere(@RequestBody Object object){
        ResponseEntity responseentity = null;

        LinkedHashMap linkedHashMap = (LinkedHashMap) object;
        
        String token = linkedHashMap.get("token").toString();
        String idcategorie = linkedHashMap.get("idcategorie").toString();
        String nomproduit = linkedHashMap.get("nomproduit").toString();
        String prixdepart = linkedHashMap.get("prixdepart").toString();
        // String base64image = linkedHashMap.get("base64image");
        String dateheuredebut = linkedHashMap.get("dateheuredebut").toString();
        String dateheurefin = linkedHashMap.get("dateheurefin").toString();
        String description = linkedHashMap.get("description").toString();

        Token tokenObject = new Token();
        tokenObject = tokenRepository.findByValueAndExpirationdateIsNull(token);   
        if (tokenObject != null) {
            Enchere enchere = new Enchere();
            enchere.setIdClient(tokenObject.getTableid());
            enchere.setNomProduit(nomproduit);
            enchere.setDescription(description);
            enchere.setPrixDepart(Double.parseDouble(prixdepart));
            enchere.setIdCategorie(Long.valueOf(idcategorie));
            enchere.setDateHeureDebut(Timestamp.valueOf(dateheuredebut.replace('T', ' ') + (":00")));
            enchere.setDateHeureFin(Timestamp.valueOf(dateheurefin.replace('T', ' ') + (":00")));
            enchereRepository.save(enchere);
            ArrayList datas = (ArrayList) linkedHashMap.get("base64image");
            
            for (Object data : datas) {
               Photo p = new Photo();
               Enchere e = enchereRepository.findByIdClientAndNomProduitAndPrixDepartAndIdCategorieAndDateHeureDebutAndDateHeureFinAndDescription(enchere.getIdClient(), enchere.getNomProduit(), enchere.getPrixDepart(), enchere.getIdCategorie(), enchere.getDateHeureDebut(), enchere.getDateHeureFin(), enchere.getDescription());
               p.setBase64image(data.toString());
               p.setIdenchere(e.getId());
                photoRepository.save(p);
            }
            
            
            enchereRepository.save(enchere);
            Succes succes = new Succes();
            HashMap codeerror = new HashMap();
            codeerror.put("message", "Enchere ajouté avec Succès !");
            succes.setData(codeerror);
            responseentity = new ResponseEntity(succes, HttpStatus.OK);
        }
        else{
            Error error = new Error();
            HashMap codeerror = new HashMap();
            codeerror.put("code", 0);
            codeerror.put("etat", "disconnected");
            error.setError(codeerror);
            responseentity = new ResponseEntity(error, HttpStatus.OK);    
        }

        return responseentity;
    }

}
