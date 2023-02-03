package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.DetailEnchere;
import com.example.demo.repository.DetailEnchereRepository;
import com.example.demo.response.Succes;

@Controller
@CrossOrigin("*")
public class DetailEnchereController {
    @Autowired
    DetailEnchereRepository detailRepository;

    @GetMapping("encheres/{id}/details")
    public ResponseEntity getDetails(@PathVariable(value = "id") Long id){

        List<DetailEnchere> liste = (List<DetailEnchere>) detailRepository.findByIdenchere(id);
        
        ResponseEntity responseentity = null;

        Succes succes = new Succes();
        succes.setData(liste);

        responseentity = new ResponseEntity(succes, HttpStatus.CREATED);

        return responseentity;
    }
}
