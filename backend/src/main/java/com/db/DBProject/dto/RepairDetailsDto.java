package com.db.DBProject.dto;

import java.util.List;

public record RepairDetailsDto(
        Integer repairCode,
        boolean status,
        ApplianceDto appliance,
        CustomerDto customer,
        List<PartDto> parts,
        List<DateActionDto> dateActionDtoList
) {
}
