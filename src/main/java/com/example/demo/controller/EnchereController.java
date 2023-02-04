package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Enchere;
import com.example.demo.model.Photo;
import com.example.demo.model.Token;
import com.example.demo.repository.EnchereRepository;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.V_EnchereRepository;
import com.example.demo.response.Succes;
import com.example.demo.response.Error;

@RestController
@CrossOrigin("*")
public class EnchereController {
    
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private V_EnchereRepository v_enchereRepository;

    @Autowired
    EnchereRepository enchereRepository;

    @GetMapping("/encheres")
    public ResponseEntity listerEnchere(){
        ResponseEntity responseentity = null;
        Succes succes = new Succes();
        succes.setData(v_enchereRepository.findAll());
        responseentity = new ResponseEntity(succes, HttpStatus.OK);
        return responseentity;
    }

    @GetMapping("/encheres/etats/{id}")
    public ResponseEntity ListerEnchere(@PathVariable(value = "id") Long idetat) throws Exception {

        List<Enchere> liste = (List<Enchere>) enchereRepository.findByIdetat(idetat);
        
        ResponseEntity responseentity = null;

        Succes succes = new Succes();
        succes.setData(liste);

        responseentity = new ResponseEntity(succes, HttpStatus.CREATED);

        return responseentity;
    }

    @PostMapping("/enchere/ajouter1")
    public ResponseEntity ajouterEnchere1(@RequestBody Object object){
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
            enchere.setIdclient(tokenObject.getTableid());
            enchere.setNomproduit(nomproduit);
            enchere.setDescription(description);
            enchere.setPrixdepart(Double.parseDouble(prixdepart));
            enchere.setIdcategorie(Long.valueOf(idcategorie));
            enchere.setDateheuredebut(Timestamp.valueOf(dateheuredebut.replace('T', ' ') + (":00")));
            enchere.setDateheurefin(Timestamp.valueOf(dateheurefin.replace('T', ' ') + (":00")));
            enchereRepository.save(enchere);
            ArrayList datas = (ArrayList) linkedHashMap.get("base64image");
            
            for (Object data : datas) {
               Photo p = new Photo();
               Enchere e = enchereRepository.findByIdClientAndNomProduitAndPrixDepartAndIdCategorieAndDateHeureDebutAndDateHeureFinAndDescription(enchere.getIdclient(), enchere.getNomproduit(), enchere.getPrixdepart(), enchere.getIdcategorie(), enchere.getDateheuredebut(), enchere.getDateheurefin(), enchere.getDescription());
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

    @PostMapping("/enchere/ajouter")
    public ResponseEntity AjouterEnchere(@RequestBody Enchere enchere) throws Exception {

        ResponseEntity responseentity = null;

        try {

            enchereRepository.save(enchere);

            Succes succes = new Succes();
            HashMap data = new HashMap();
            data.put("message", "Success");
            succes.setData(data);
            responseentity = new ResponseEntity(succes, HttpStatus.CREATED);
            return responseentity;
        }
        catch(Exception ex){
            Error error = new Error();

            HashMap errordata = new HashMap();
            errordata.put("code", "404");
            errordata.put("message", "Failed");

            error.setError(errordata);
            responseentity = new ResponseEntity(error, HttpStatus.CREATED);
            return responseentity;
        }
    }


    // @GetMapping("clients/{id}/encheres")
    // public ResponseEntity MesEncheres(@PathVariable(value = "id") Long id) throws Exception {

    //     List<Enchere> liste = (List<Enchere>) enchereRepository.findByIdclient(id);
        
    //     ResponseEntity responseentity = null;

    //     Succes succes = new Succes();
    //     succes.setData(liste);

    //     responseentity = new ResponseEntity(succes, HttpStatus.CREATED);

    //     return responseentity;
    // }
}
