package com.db.DBProject.controllers;

import com.db.DBProject.dto.PartCountDto;
import com.db.DBProject.dto.PartDto;
import com.db.DBProject.models.Part;
import com.db.DBProject.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PartController {

    @Autowired
    private PartService partService;

    @GetMapping(value = "parts")
    public ResponseEntity<List<PartCountDto>> getAllParts() {
        return ResponseEntity.ok().body(partService.getParts());
    }

    @GetMapping(value = "parts/freeCode")
    public ResponseEntity<Integer> getFirstFreeCode(){
        return ResponseEntity.ok().body(partService.fingFirstFreePartCode());
    }

    @PostMapping(value = "parts")
    public ResponseEntity<PartCountDto> addPart(@RequestBody PartDto partBody) {
        try {
            return ResponseEntity.ok().body(
                    partService.mapToCountDto(
                            partService.savePart(
                                    partService.mapToPart(partBody)
                            )
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "parts/{part_code}")
    public ResponseEntity<Boolean> deletePart(@PathVariable Integer part_code){
        Part part = partService.findOne(part_code);
        if(part != null){
            partService.deletePart(part);
            return ResponseEntity.ok().body(true);
        }else return ResponseEntity.badRequest().build();
    }

}
