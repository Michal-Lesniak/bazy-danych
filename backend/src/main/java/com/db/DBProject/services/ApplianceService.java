package com.db.DBProject.services;

import com.db.DBProject.dto.ApplianceDto;
import com.db.DBProject.models.Appliance;
import com.db.DBProject.repositories.ApplianceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ApplianceService {

    @Autowired
    private ApplianceRepository applianceRepository;

    public Appliance mapToAppliance(ApplianceDto appliance) {
        Appliance newAppliance = new Appliance();
        newAppliance.setApplianceCode(appliance.applianceCode());
        newAppliance.setName(appliance.name());
        newAppliance.setPhotoURL(appliance.photoURL());
        return newAppliance;
    }

    public ApplianceDto mapToApplianceDto(Appliance appliance) {
        return new ApplianceDto(
                appliance.getApplianceCode(),
                appliance.getName(),
                appliance.getPhotoURL()
        );
    }

    public Appliance findOne(Integer applianceCode){
        Optional<Appliance> appliance = applianceRepository.findByApplianceCode(applianceCode);
        return appliance.orElse(null);
    }

    public Integer returnFirstFreeApplianceCode(){
        List<Appliance> appliances = applianceRepository.findAll();
        Set<Integer> codes = appliances.stream().map(Appliance::getApplianceCode).sorted().collect(Collectors.toSet());

        for(int i=1; i < Collections.max(codes); i++){
            if(!codes.contains(i)){
                return i;
            }
        }

        return Collections.max(codes) + 1;
    }

    @Transactional
    public void deleteAppliance(Appliance appliance){applianceRepository.delete(appliance);}

    public List<ApplianceDto> getAppliances() {
        return applianceRepository.findAll().stream().map(this::mapToApplianceDto).collect(Collectors.toList());
    }

    @Transactional
    public Appliance saveAppliance(Appliance appliance) throws Exception {
        if (applianceRepository.findByApplianceCode(appliance.getApplianceCode()).isPresent()) throw new Exception("Sprzęt istnieje");
        else return applianceRepository.save(appliance);
    }
}
