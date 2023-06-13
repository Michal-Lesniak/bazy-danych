package com.db.DBProject.services;

import com.db.DBProject.dto.*;
import com.db.DBProject.models.*;
import com.db.DBProject.repositories.*;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RepairService {


    @Autowired
    private DateActionService dateActionService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PartService partService;

    @Autowired
    private ApplianceService applianceService;

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

    private RepairDetailsDto mapToDetailsDto(Repair repair){
        CustomerDto customerDto = customerService.mapToDto(repair.getCustomer());
        ApplianceDto applianceDto = applianceService.mapToApplianceDto(repair.getAppliance());

        return new RepairDetailsDto(
                repair.getRepairCode(),
                repair.getStatus(),
                applianceDto,
                customerDto,
                repair.getPart().stream().map(element -> partService.mapToDto(element)).collect(Collectors.toList()),
                repair.getDateActions().stream().map(element -> dateActionService.mapToDto(element)).collect(Collectors.toList())
        );
    }

    private AddRepairDto mapToAddDtoFromDto(RepairDto repairDto) {
        return new AddRepairDto(
                repairDto.repairCode(),
                repairDto.status(),
                repairDto.customerCode(),
                repairDto.applianceCode(),
                repairDto.partCodes(),
                repairDto.listOfDateCode().stream().map(element -> dateActionService.getDateActionByDateCode(element)).collect(Collectors.toList())
        );
    }



    private Repair mapToRepair(AddRepairDto addRepairDto) {
        Optional<Customer> customer = customerRepository.findByUserCode(addRepairDto.customerCode());
        Optional<Appliance> appliance = applianceRepository.findByApplianceCode(addRepairDto.applianceCode());

        List<Part> parts = new ArrayList<>();
        List<DateAction> dateActions = new ArrayList<>();
        Customer customerElement;
        Appliance applianceElement;

        try {
            if (customer.isPresent() && appliance.isPresent()) {
                customerElement = customer.get();
                applianceElement = appliance.get();
            } else throw new NoSuchElementException();

            addRepairDto.partCodes().forEach(element -> {
                Optional<Part> part = partRepository.findByPartCode(element);
                if (part.isPresent()) {
                    parts.add(part.get());
                } else throw new NoSuchElementException();
            });

        } catch (NoSuchElementException e) {
            return null;
        }

        addRepairDto.dateActionDtoList().forEach(element -> {
            dateActions.add(dateActionService.mapToDateAction(element));
        });

        Repair repair = new Repair();
        repair.setRepairCode(addRepairDto.repairCode());
        repair.setCustomer(customerElement);
        repair.setAppliance(applianceElement);
        repair.setPart(parts);
        repair.setDateActions(dateActions);

        return repair;
    }

    public Repair findOne(Integer repairCode){
        Optional<Repair> repair = repairRepository.findByRepairCode(repairCode);
        return repair.orElse(null);
    }

    public Integer findFirstFreeRepairCode(){
        List<Repair> repairs = repairRepository.findAll();
        Set<Integer> codes = repairs.stream().map(Repair::getRepairCode).collect(Collectors.toSet());

        for(int i = 1; i < Collections.max(codes); i++){
            if(!codes.contains(i)){
                return i;
            }
        }

        return Collections.max(codes) + 1;
    }

    @Transactional
    public void deleteRepair(Repair repair){
        repairRepository.delete(repair);
    }



    public List<RepairDto> getRepairs() {
        return repairRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<RepairDetailsDto> getRepairsDetails() {
        return repairRepository.findAll().stream().map(this::mapToDetailsDto).collect(Collectors.toList());
    }

    public Repair getRepair(Integer repairCode) {
        Optional<Repair> repair = repairRepository.findByRepairCode(repairCode);
        if (repair.isPresent()) {
            return repair.get();
        } else throw new NotFoundException("Nie znaleziono");
    }

    public RepairDto getRepairDto(Integer repairCode) {
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

    @Transactional
    public Repair updateStatus(RepairStatusDto repairStatusDto){
        Optional<Repair> repairToUpdate = repairRepository.findByRepairCode(repairStatusDto.repairCode());

        if(repairToUpdate.isEmpty()){
            throw new NotFoundException("Nie znaleziono naprawy w Bazie");
        }
        Repair existingRepair = repairToUpdate.get();
        existingRepair.setStatus(repairStatusDto.status());
        return repairRepository.save(existingRepair);
    }

    @Transactional
    public Repair saveRepair(Repair repair){
        if(repairRepository.findByRepairCode(repair.getRepairCode()).isPresent()){
            throw new IllegalArgumentException("Naprawa o podanym kodzie istnieje");
        } else {
            return repairRepository.save(repair);
        }
    }

    @Transactional
    public RepairDto addRepair(AddRepairDto addRepairDto) throws Exception {
        Repair repair = mapToRepair(addRepairDto);
        if (repair != null) {
            Repair updatedRepair = saveRepair(repair);
            repair.getDateActions().forEach(el -> {
                dateActionService.setRepair(el, updatedRepair);
            });
            return mapToDto(repair);
        } else throw new Exception("Naprawa jest Null");
    }



}
