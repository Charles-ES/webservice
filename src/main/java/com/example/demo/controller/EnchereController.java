package com.example.demo.controller;

import java.util.HashMap;
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
import com.example.demo.repository.EnchereRepository;
import com.example.demo.response.Succes;
import com.example.demo.response.Error;

@RestController
@CrossOrigin("*")
public class EnchereController {
    
    @Autowired
    EnchereRepository enchereRepository;

    @GetMapping("/encheres/etats/{id}")
    public ResponseEntity ListerEnchere(@PathVariable(value = "id") Long idetat) throws Exception {

        List<Enchere> liste = (List<Enchere>) enchereRepository.findByIdetat(idetat);
        
        ResponseEntity responseentity = null;

        Succes succes = new Succes();
        succes.setData(liste);

        responseentity = new ResponseEntity(succes, HttpStatus.CREATED);

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
