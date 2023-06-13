package com.db.DBProject.controllers;

import com.db.DBProject.dto.*;
import com.db.DBProject.models.DateAction;
import com.db.DBProject.models.Repair;
import com.db.DBProject.services.DateActionService;
import com.db.DBProject.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
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

    @GetMapping(value = "/repairsDetails")
    public ResponseEntity<List<RepairDetailsDto>> getRepairsDetail() {
        return ResponseEntity.ok().body(repairService.getRepairsDetails());
    };

    @GetMapping(value = "/repairs/freeCode")
    public ResponseEntity<Integer> getFirstFreeCode(){
        return ResponseEntity.ok().body(repairService.findFirstFreeRepairCode());
    }

    @GetMapping(value = "/repairs/{repair_code}")
    public ResponseEntity<RepairDto> getRepair(@PathVariable(value = "repair_code") Integer repairCode) {
        try {
            RepairDto repairDto = repairService.getRepairDto(repairCode);
            return ResponseEntity.ok().body(repairDto);
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

    @PutMapping(value = "/repairs/status")
    public ResponseEntity<Boolean> updateStatus(@RequestBody RepairStatusDto repairStatusDto) {
        try{
            repairService.updateStatus(repairStatusDto);
            return ResponseEntity.ok().body(true);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/repairs")
    public ResponseEntity<RepairDto> addRepair(@RequestBody AddRepairDto addRepairDto) {
        try {
            RepairDto repairDto = repairService.addRepair(addRepairDto);
            return ResponseEntity.ok().body(repairDto);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/repairs/{repair_code}")
    public ResponseEntity<Boolean> deleteRepair(@PathVariable Integer repair_code) {
        Repair repair = repairService.findOne(repair_code);
        if (repair != null) {
            repairService.deleteRepair(repair);
            return ResponseEntity.ok().body(true);
        } else return ResponseEntity.badRequest().build();
    }


    @PostMapping(value = "/repairs/{repair_code}/date")
    public ResponseEntity<DateAction> addDateToRepair(@PathVariable Integer repair_code, @RequestBody DateActionDto dateActionDto) {
        try {
            return ResponseEntity.ok().body(dateActionService.setRepairAndSave(dateActionDto,repair_code));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/repairs/{repair_code}/date")
    public ResponseEntity<List<DateActionDto>> getDates(@PathVariable(value = "repair_code") Integer repairCode) {
        try {
            RepairDto repairDto = repairService.getRepairDto(repairCode);
            List<DateActionDto> dateActionDtos = new ArrayList<>();
            repairDto.listOfDateCode().forEach(element ->
                dateActionDtos.add(dateActionService.getDateActionByDateCode(element))
        );
            return ResponseEntity.ok().body(dateActionDtos);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
