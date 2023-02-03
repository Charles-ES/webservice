package com.example.demo.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Demanderecharge;
import com.example.demo.model.Etat;
import com.example.demo.model.Token;
import com.example.demo.repository.DemanderechargeRepository;
import com.example.demo.repository.EtatRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.response.Succes;
import com.example.demo.response.Error;

@RestController
@CrossOrigin("*")
public class DemanderechargeController {

    @Autowired
    private DemanderechargeRepository demanderechargeRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private EtatRepository etatRepository;

    @PostMapping("/compte/recharger1")
    public ResponseEntity Recharger(@RequestBody Object object) throws Exception {

        ResponseEntity responseentity = null;

        LinkedHashMap linkedHashMap = (LinkedHashMap) object;
        String token = linkedHashMap.get("token").toString();
        String montant = linkedHashMap.get("montant").toString();

        Token tokenObject = new Token();
        tokenObject = tokenRepository.findByValueAndExpirationdateIsNull(token);
        if (tokenObject != null) {
            Demanderecharge demanderecharge = new Demanderecharge();
            demanderecharge.setIdclient(tokenObject.getTableid());
            demanderecharge.setMontant(Double.parseDouble(montant));

            Etat etat = new Etat();
            etat = etatRepository.findByType("non valide");
            demanderecharge.setIdetat(etat.getId());
            demanderecharge.setDatedemanderecharge(new Date(System.currentTimeMillis()));
            demanderechargeRepository.save(demanderecharge);

            Succes succes = new Succes();
            HashMap message = new HashMap();
            message.put("message", "Compte rechargé avec Succès !");
            succes.setData(message);
            responseentity = new ResponseEntity(succes, HttpStatus.OK);
        } else {
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
