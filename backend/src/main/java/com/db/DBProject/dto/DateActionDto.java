package com.db.DBProject.dto;

import java.util.Date;

public record DateActionDto(
        Integer dateCode,
        Integer repairCode,
        String nameOfDate,
        Date date
) {}
