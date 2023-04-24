package com.db.DBProject.controllers;

import com.db.DBProject.dto.AddRepairDto;
import com.db.DBProject.dto.RepairDto;
import com.db.DBProject.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
public class RepairController {

    @Autowired
    private RepairService repairService;

    @GetMapping(value = "/repairs")
    public ResponseEntity<List<RepairDto>> getRepairs() {
        return ResponseEntity.ok().body(repairService.getRepairs());
    }

    @GetMapping(value = "/repairs/{repair_code}")
    public ResponseEntity<RepairDto> getRepair(@PathVariable(value = "repair_code") Integer repairCode) {
        try {
            RepairDto repair = repairService.getRepair(repairCode);
            return ResponseEntity.ok().body(repair);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/repairs/customers/{customer}")
    public ResponseEntity<List<RepairDto>> getRepairsByCustomerCode(@PathVariable(value = "customer") Integer customerCode) {
        try {
            return ResponseEntity.ok().body(repairService.getRepairsByCustomerCode(customerCode));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/repairs")
    public ResponseEntity<RepairDto> addRepair(@RequestBody AddRepairDto addRepairDto) {
        RepairDto repairDto = repairService.addRepair(addRepairDto);
        if (repairDto != null) {
            return ResponseEntity.ok().body(repairDto);
        } else return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/repairs/{repair_code}")
    public ResponseEntity<List<RepairDto>> deleteRepair(@PathVariable Integer repair_code) {
        return null;
    }
}
