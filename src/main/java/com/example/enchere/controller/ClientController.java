package com.example.enchere.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.model.Client;
import com.example.enchere.model.Token;
import com.example.enchere.repository.ClientRepository;
import com.example.enchere.repository.TokenRepository;
import com.example.enchere.response.Succes;
import com.example.enchere.response.Error;

@RestController
@CrossOrigin("*")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TokenRepository tokenRepository;

    @PostMapping("/client/register")
    public ResponseEntity register(@RequestBody Client client) throws Exception {

        Client temp = client;
        client = clientRepository.findByTelephoneAndMotdepasse(client.getTelephone(),
                Token.sha1(client.getMotdepasse()));

        ResponseEntity responseentity = null;

        if (client == null) {
            temp.setMotdepasse(Token.sha1(temp.getMotdepasse()));
            clientRepository.save(temp);
            Succes succes = new Succes();

            HashMap succesdata = new HashMap();
            succesdata.put("message", "Client Inscrit avec Succès !");

            succes.setData(succesdata);
            responseentity = new ResponseEntity(succes, HttpStatus.OK);
            return responseentity;
        }

        Error error = new Error();

        HashMap errordata = new HashMap();
        errordata.put("message", "user already exists");

        error.setError(errordata);
        errordata = null;

        responseentity = new ResponseEntity(error, HttpStatus.OK);
        return responseentity;

    }

    @PostMapping("/client/login")
    public ResponseEntity login(@RequestBody Client client) throws Exception {

        client = clientRepository.findByTelephoneAndMotdepasse(client.getTelephone(),
                Token.sha1(client.getMotdepasse()));

        ResponseEntity responseentity = null;

        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (client != null) {
            Token token = new Token();
            token.setTableid(client.getId());
            token.setTablename("client");
            token.setValueToHash("/client/login" + client.getId() + now); // url + id + timestamp

            Token temp = tokenRepository.findByTableidAndTablenameAndExpirationdateIsNull(token.getTableid(),
                    token.getTablename());

            if (temp == null) {
                tokenRepository.save(token);
            } else {
                token = temp;
            }
            temp = null;

            Succes succes = new Succes();
            succes.setData(token);

            token = null;
            responseentity = new ResponseEntity(succes, HttpStatus.OK);

            succes = null;
            client = null;
            return responseentity;
        }

        Error error = new Error();

        HashMap errordata = new HashMap();
        errordata.put("code", "404");
        errordata.put("message", "Client inexistant !");

        error.setError(errordata);
        errordata = null;

        responseentity = new ResponseEntity(error, HttpStatus.OK);
        return responseentity;
    }

    @PutMapping("/client/logout")
    public ResponseEntity logout(@RequestBody Token token) throws Exception {
        ResponseEntity responseentity = null;
        token = tokenRepository.findByValue(token.getValue());

        if (token != null) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            token.setExpirationdate(now);
            now = null;

            Token temp = tokenRepository.findByValueAndExpirationdateIsNull(token.getValue());
            if (temp != null) {
                tokenRepository.save(token);

                Succes succes = new Succes();
                HashMap data = new HashMap();
                data.put("message", "Logged out");
                succes.setData(data);
                responseentity = new ResponseEntity(succes, HttpStatus.OK);
                succes = null;
                return responseentity;
            }
            temp = null;
            token = null;
        }

        Error error = new Error();

        HashMap errordata = new HashMap();
        errordata.put("code", "404");
        errordata.put("message", "token already updated or not found");

        error.setError(errordata);
        errordata = null;

        responseentity = new ResponseEntity(error, HttpStatus.CREATED);
        error = null;
        return responseentity;

    }


    @GetMapping("/token/{value}")
    public ResponseEntity getClient(@PathVariable("value") String value){
        Token token = tokenRepository.findByTablenameAndValueAndExpirationdateIsNull("client", value);
        if (token != null) {
            Succes succes = new Succes();
            succes.setData(clientRepository.findById(token.getTableid()));
            return new ResponseEntity(succes, HttpStatus.OK);
        }
        Error error = new Error();
        error.setError("error");
        return new ResponseEntity(error, HttpStatus.OK);
    }
}
