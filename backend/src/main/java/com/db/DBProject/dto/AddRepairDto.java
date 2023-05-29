package com.db.DBProject.dto;

import com.db.DBProject.models.Date;

import java.util.List;

public record AddRepairDto(Integer repairCode,
                           boolean status,
                           Integer customerCode,
                           Integer applianceCode,
                           List<Integer> partCodes,
                           List<Date> listOfDate) {
}
