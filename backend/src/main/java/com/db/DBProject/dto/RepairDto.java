package com.db.DBProject.dto;

import java.util.List;

public record RepairDto(Integer repairCode,
                        boolean status,
                        Integer customerCode,
                        Integer applianceCode,
                        List<Integer> partCodes) {
}
