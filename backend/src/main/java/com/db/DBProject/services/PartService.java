package com.db.DBProject.services;

import com.db.DBProject.dto.ApplianceDto;
import com.db.DBProject.dto.PartCountDto;
import com.db.DBProject.dto.PartDto;
import com.db.DBProject.models.Appliance;
import com.db.DBProject.models.Part;
import com.db.DBProject.repositories.ApplianceRepository;
import com.db.DBProject.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartService {

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private PartRepository partRepository;

    public Part mapToPart(PartDto part) throws Exception {
        Optional<Appliance> appliance = applianceRepository.findByApplianceCode(part.applianceCode());
        if (appliance.isPresent()) {
            Part newPart = new Part();
            newPart.setPartCode(part.partCode());
            newPart.setName(part.name());
            newPart.setPrice(part.price());
            newPart.setAppliance(appliance.get());
            newPart.setPhotoURL(part.photoURL());
            return newPart;
        } else throw new Exception("Nie znaleziono");
    }

    public PartCountDto mapToCountDto(Part part) {
        return new PartCountDto(
                part.getPartCode(),
                part.getName(),
                part.getPrice(),
                part.getAppliance().getApplianceCode(),
                part.getPhotoURL(),
                part.getAmount()
        );
    }

    public List<PartCountDto> getParts() {
        return partRepository.findAll().stream().map(this::mapToCountDto).collect(Collectors.toList());
    }

    public Part savePart(Part part) throws Exception {
        if (partRepository.findByPartCode(part.getPartCode()).isPresent()) throw new Exception("Część istnieje");
        else return partRepository.save(part);
    }
}
