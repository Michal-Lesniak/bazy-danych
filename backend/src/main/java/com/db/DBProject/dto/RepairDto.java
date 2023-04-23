package com.db.DBProject.dto;

import com.db.DBProject.models.Dates;

import java.util.List;

public record RepairDto(Integer repairCode,
                        boolean status,
                        Integer customerCode,
                        Integer applianceCode,
                        List<Integer> partCodes,
                        Dates dates
                        ) {
}
