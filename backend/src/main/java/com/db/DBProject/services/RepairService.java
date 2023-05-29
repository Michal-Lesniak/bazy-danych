package com.db.DBProject.services;

import com.db.DBProject.dto.AddRepairDto;
import com.db.DBProject.dto.RepairDto;
import com.db.DBProject.models.*;
import com.db.DBProject.repositories.ApplianceRepository;
import com.db.DBProject.repositories.CustomerRepository;
import com.db.DBProject.repositories.PartRepository;
import com.db.DBProject.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private PartRepository partRepository;

    private RepairDto mapToDto(Repair repair) {
        return new RepairDto(
                repair.getRepairCode(),
                repair.getStatus(),
                repair.getCustomer().getUserCode(),
                repair.getAppliance().getApplianceCode(),
                repair.getPart().stream().map(Part::getPartCode).collect(Collectors.toList()),
                repair.getDates()
                );
    }

    private AddRepairDto mapToAddDtoFromDto(RepairDto repairDto) {
        return new AddRepairDto(
                repairDto.repairCode(),
                repairDto.status(),
                repairDto.customerCode(),
                repairDto.applianceCode(),
                repairDto.partCodes(),
                repairDto.listOfDate()
        );
    }



    private Repair mapToRepair(AddRepairDto repairDto) {
        Optional<Customer> customer = customerRepository.findByUserCode(repairDto.customerCode());
        Optional<Appliance> appliance = applianceRepository.findByApplianceCode(repairDto.applianceCode());
        List<Part> parts = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        Customer customerElement;
        Appliance applianceElement;

        try {
            if (customer.isPresent() && appliance.isPresent()) {
                customerElement = customer.get();
                applianceElement = appliance.get();
            } else throw new NoSuchElementException();
            repairDto.partCodes().forEach(element -> {
                Optional<Part> part = partRepository.findByPartCode(element);
                if (part.isPresent()) {
                    parts.add(part.get());
                } else throw new NoSuchElementException();
            });
        } catch (NoSuchElementException e) {
            return null;
        }


        Repair repair = new Repair();
        repair.setRepairCode(repairDto.repairCode());
        repair.setCustomer(customerElement);
        repair.setAppliance(applianceElement);
        repair.setPart(parts);
        repair.setDates(repairDto.listOfDate());

        return repair;
    }


    public List<RepairDto> getRepairs() {
        return repairRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public RepairDto getRepair(Integer repairCode) {
        Optional<Repair> repair = repairRepository.findByRepairCode(repairCode);
        if (repair.isPresent()) {
            return mapToDto(repair.get());
        } else throw new NotFoundException("Nie znaleziono");
    }

    public List<RepairDto> getRepairsByCustomerCode(Integer customer_code) {
        Optional<Customer> customer = customerRepository.findByUserCode(customer_code);
        if (customer.isPresent()) {
            return repairRepository.findByCustomer(customer.get()).stream().map(this::mapToDto).collect(Collectors.toList());
        } else throw new NotFoundException("Nie znaleziono");
    }

    public RepairDto addRepair(AddRepairDto addRepairDto) {
        Repair repair = mapToRepair(addRepairDto);
        if (repair != null) {
            return mapToDto(repairRepository.save(repair));
        } else return null;
    }

    public RepairDto addDateToRepair(Date date, RepairDto repairDto){
        AddRepairDto addRepairDto = mapToAddDtoFromDto(repairDto);
        Repair repair = mapToRepair(addRepairDto);
        repair.getDates().add(date);
        if(repair != null) {
            return mapToDto(repairRepository.save(repair));
        } else return null;
    }
}
