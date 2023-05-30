package com.db.DBProject.services;

import com.db.DBProject.dto.DateActionDto;
import com.db.DBProject.models.DateAction;
import com.db.DBProject.repositories.DateActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
public class DateActionService {

    @Autowired
    private DateActionRepository dateActionRepository;

    public DateActionDto mapToDto(DateAction dateAction){
        return new DateActionDto(
                dateAction.getDateCode(),
                dateAction.getNameOfDate(),
                dateAction.getDate());
    }

    public DateAction mapToDateAction(DateActionDto dateActionDto){
       DateAction dateAction = new DateAction();
       dateAction.setDateCode(dateAction.getDateCode());
       dateAction.setNameOfDate(dateActionDto.nameOfDate());
       dateAction.setDate(dateActionDto.date());
       return dateAction;
    }

    public DateActionDto getDateActionByDateCode(Integer dateCode){
        Optional<DateAction> dateAction = dateActionRepository.findByDateCode(dateCode);
        if(dateAction.isPresent()){
            return mapToDto(dateAction.get());
        }else throw new NotFoundException("Nie znaleziono");
    }
}
