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

}
