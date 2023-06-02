package com.db.DBProject.controllers;

import com.db.DBProject.models.DateAction;
import com.db.DBProject.repositories.DateActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DateActionController {

    @Autowired
    DateActionRepository dateActionRepository;

    @GetMapping(value = "/dates")
    public ResponseEntity<List<DateAction>> getDateActions(){return ResponseEntity.ok().body(dateActionRepository.findAll());}

}
