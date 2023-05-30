package com.db.DBProject.controllers;

import com.db.DBProject.dto.AddRepairDto;
import com.db.DBProject.dto.DateActionDto;
import com.db.DBProject.dto.RepairDto;
import com.db.DBProject.models.DateAction;
import com.db.DBProject.services.DateActionService;
import com.db.DBProject.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RepairController {

    @Autowired
    private DateActionService dateActionService;

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


    @PostMapping(value = "/repairs/{repair_code}/date")
    public ResponseEntity<RepairDto> addDateToRepair(@PathVariable Integer repair_code, @RequestBody DateActionDto dateActionDto) {
        try {
            RepairDto repair = repairService.getRepair(repair_code);
            DateAction dateAction = dateActionService.mapToDateAction(dateActionDto);
            RepairDto updatedRepair =  repairService.addDateToRepair(dateAction, repair);
            return ResponseEntity.ok().body(updatedRepair);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/repairs/{repair_code}/date")
    public ResponseEntity<List<DateActionDto>> getDates(@PathVariable(value = "repair_code") Integer repairCode) {
        try {
            RepairDto repair = repairService.getRepair(repairCode);
            List<DateActionDto> dateActionDtos = new ArrayList<>();
            repair.listOfDateCode().forEach(element ->
                dateActionDtos.add(dateActionService.getDateActionByDateCode(element))
        );
            return ResponseEntity.ok().body(dateActionDtos);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
