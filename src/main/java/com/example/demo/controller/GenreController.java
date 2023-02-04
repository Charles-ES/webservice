package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.GenreRepository;
import com.example.demo.response.Succes;

@RestController
@CrossOrigin("*")
public class GenreController {

    @Autowired
    GenreRepository genreRepository;
    
    @GetMapping("/genres")
    public ResponseEntity getAllGenre(){
        ResponseEntity responseentity = null;
        Succes succes = new Succes();
        succes.setData(genreRepository.findAll());
        responseentity = new ResponseEntity(succes, HttpStatus.OK);
        return responseentity;
    }
}
