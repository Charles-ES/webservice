package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.EnchereView;
import com.example.demo.repository.EnchereViewRepository;
import com.example.demo.response.Succes;

@Controller
@CrossOrigin("*")
public class EnchereViewController {
    
    @Autowired
    EnchereViewRepository enchereViewRepository;

    @GetMapping("/viewencheres")
    public ResponseEntity ListerEnchereView() throws Exception {

        List<EnchereView> liste = (List<EnchereView>) enchereViewRepository.findAll();
        
        ResponseEntity responseentity = null;

        Succes succes = new Succes();
        succes.setData(liste);

        responseentity = new ResponseEntity(succes, HttpStatus.CREATED);

        return responseentity;
    }

    @GetMapping("/clients/{id}/encheres")
    public ResponseEntity ListerEnchereView(@PathVariable(value = "id") Long id) throws Exception {

        List<EnchereView> liste = (List<EnchereView>) enchereViewRepository.findByIdclient(id);
        
        ResponseEntity responseentity = null;

        Succes succes = new Succes();
        succes.setData(liste);

        responseentity = new ResponseEntity(succes, HttpStatus.CREATED);

        return responseentity;
    }

}
