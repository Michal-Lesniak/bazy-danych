package com.db.DBProject.dto;

import java.util.Date;
import java.util.List;

public record AddRepairDto(Integer repairCode,
                           boolean status,
                           Integer customerCode,
                           Integer applianceCode,
                           List<Integer> partCodes,
                           Date registerDate) {
}
