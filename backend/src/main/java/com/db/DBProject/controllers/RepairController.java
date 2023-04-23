package com.db.DBProject.controllers;

import com.db.DBProject.dto.AddRepairDto;
import com.db.DBProject.dto.RepairDto;
import com.db.DBProject.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepairController {

    @Autowired
    private RepairService repairService;

    @GetMapping(value = "/repair")
    public ResponseEntity<List<RepairDto>> getRepairs() {
        return ResponseEntity.ok().body(repairService.getRepairs());
    }

    @PostMapping(value = "/repair")
    public ResponseEntity<RepairDto> addRepair(@RequestBody AddRepairDto addRepairDto) {
        RepairDto repairDto = repairService.addRepair(addRepairDto);
        if (repairDto != null) {
            return ResponseEntity.ok().body(repairDto);
        } else return ResponseEntity.badRequest().build();
    }
}
