package com.db.DBProject.controllers;

import com.db.DBProject.dto.ApplianceDto;
import com.db.DBProject.models.Appliance;
import com.db.DBProject.services.ApplianceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @GetMapping(value = "appliances")
    public ResponseEntity<List<ApplianceDto>> getAppliances() {
        return ResponseEntity.ok().body(applianceService.getAppliances());
    }

    @PostMapping(value = "appliances")
    public ResponseEntity<ApplianceDto> addAppliance(@RequestBody ApplianceDto applianceBody) {
        try {
            return ResponseEntity.ok().body(
                    applianceService.mapToApplianceDto(
                            applianceService.saveAppliance(
                                    applianceService.mapToAppliance(applianceBody)
                            )
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "appliances/{appliance_code}")
    public ResponseEntity<Boolean> deleteAppliance(@PathVariable Integer appliance_code){
        Appliance appliance = applianceService.findOne(appliance_code);
        if(appliance != null){
            applianceService.deleteAppliance(appliance);
            return ResponseEntity.ok().body(true);
        }else return ResponseEntity.badRequest().build();
    }
}
