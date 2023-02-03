package com.example.enchere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.model.Categorie;
import com.example.enchere.response.Succes;
import com.example.enchere.service.CategorieService;


@CrossOrigin(origins = { "*" }, maxAge = 4800, allowCredentials = "false")
@RestController
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping("/categorie")
    public Iterable<Categorie> getCategories() {
        return categorieService.getCategories();
    }

    @GetMapping("/categories")
    public ResponseEntity listerCategorie() {
        ResponseEntity responseentity = null;
        Succes succes = new Succes();
        succes.setData(categorieService.listerCategorie());
        responseentity = new ResponseEntity(succes, HttpStatus.OK);
        return responseentity;
    }
}
