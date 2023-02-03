package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Compte;
import com.example.demo.repository.CompteRepository;
import com.example.demo.response.Succes;
import com.example.demo.response.Error;

@RestController
@CrossOrigin("*")
public class CompteController {
    
    @Autowired
    CompteRepository compteRepository;

    @PostMapping("/compte/recharger")
    public ResponseEntity Recharger(@RequestBody Compte compte) throws Exception {

        ResponseEntity responseentity = null;

        try {

            compteRepository.save(compte);

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
}
