package com.db.DBProject.services;

import com.db.DBProject.dto.DateActionDto;
import com.db.DBProject.models.DateAction;
import com.db.DBProject.models.Repair;
import com.db.DBProject.repositories.DateActionRepository;
import com.db.DBProject.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
public class DateActionService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private DateActionRepository dateActionRepository;

    public DateActionDto mapToDto(DateAction dateAction){
        return new DateActionDto(
                dateAction.getDateCode(),
                dateAction.getRepair().getRepairCode(),
                dateAction.getNameOfDate(),
                dateAction.getDate());
    }

    public DateAction mapToDateAction(DateActionDto dateActionDto){
        Optional<Repair> repair = repairRepository.findByRepairCode(dateActionDto.repairCode());

        DateAction dateAction = new DateAction();
        if(repair.isPresent()){
            dateAction.setRepair(repair.get());
        }

        dateAction.setDateCode(dateActionDto.dateCode());
        dateAction.setNameOfDate(dateActionDto.nameOfDate());
        dateAction.setDate(dateActionDto.date());
        return dateAction;
    }

    public DateAction addDateAction(DateActionDto dateActionDto){
        DateAction dateAction = mapToDateAction(dateActionDto);
        return dateActionRepository.save(dateAction);
    }

    public DateActionDto getDateActionByDateCode(Integer dateCode){
        Optional<DateAction> dateAction = dateActionRepository.findByDateCode(dateCode);
        if(dateAction.isPresent()){
            return mapToDto(dateAction.get());
        }else throw new NotFoundException("Nie znaleziono");
    }
}
