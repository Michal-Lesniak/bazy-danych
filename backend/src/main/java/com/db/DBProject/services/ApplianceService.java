package com.db.DBProject.services;

import com.db.DBProject.dto.ApplianceDto;
import com.db.DBProject.models.Appliance;
import com.db.DBProject.repositories.ApplianceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
