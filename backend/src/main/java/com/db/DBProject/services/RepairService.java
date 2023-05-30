package com.db.DBProject.services;

import com.db.DBProject.dto.AddRepairDto;
import com.db.DBProject.dto.RepairDto;
import com.db.DBProject.models.*;
import com.db.DBProject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RepairService {

    @Autowired
    private DateActionRepository dateActionRepository;

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
                repair.getDateActions().stream().map(DateAction::getDateCode).collect(Collectors.toList())
                );
    }

    private AddRepairDto mapToAddDtoFromDto(RepairDto repairDto) {
        return new AddRepairDto(
                repairDto.repairCode(),
                repairDto.status(),
                repairDto.customerCode(),
                repairDto.applianceCode(),
                repairDto.partCodes(),
                repairDto.listOfDateCode()
        );
    }



    private Repair mapToRepair(AddRepairDto repairDto) {
        Optional<Customer> customer = customerRepository.findByUserCode(repairDto.customerCode());
        Optional<Appliance> appliance = applianceRepository.findByApplianceCode(repairDto.applianceCode());

        List<Part> parts = new ArrayList<>();
        List<DateAction> dateActions = new ArrayList<>();
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

            repairDto.listOfDateCode().forEach(element -> {
                Optional<DateAction> date = dateActionRepository.findByDateCode(element);
                if(date.isPresent()){
                    dateActions.add(date.get());
                }else throw new NoSuchElementException();
            });

        } catch (NoSuchElementException e) {
            return null;
        }


        Repair repair = new Repair();
        repair.setRepairCode(repairDto.repairCode());
        repair.setCustomer(customerElement);
        repair.setAppliance(applianceElement);
        repair.setPart(parts);
        repair.setDateActions(dateActions);

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

    public RepairDto addDateToRepair(DateAction dateAction, RepairDto repairDto){
        AddRepairDto addRepairDto = mapToAddDtoFromDto(repairDto);
        Repair repair = mapToRepair(addRepairDto);
        repair.getDateActions().add(dateAction);
        if(repair != null) {
            return mapToDto(repairRepository.save(repair));
        } else return null;
    }

}
