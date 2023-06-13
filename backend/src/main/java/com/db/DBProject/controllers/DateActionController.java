package com.db.DBProject.controllers;

import com.db.DBProject.models.DateAction;
import com.db.DBProject.repositories.DateActionRepository;
import com.db.DBProject.services.DateActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class DateActionController {

    @Autowired
    DateActionRepository dateActionRepository;

    @Autowired
    DateActionService dateActionService;

    @GetMapping(value = "/dates")
    public ResponseEntity<List<DateAction>> getDateActions(){return ResponseEntity.ok().body(dateActionRepository.findAll());}

    @GetMapping(value = "dates/freeCode")
    public ResponseEntity<Integer> getFirstFreeCode(){
        return ResponseEntity.ok().body(dateActionService.findFirstFreeDateCode());
    }
}
