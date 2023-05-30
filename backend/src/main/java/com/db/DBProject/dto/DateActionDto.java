package com.db.DBProject.dto;

import java.util.Date;

public record DateActionDto(
        Integer dateCode,
        String nameOfDate,
        Date date
) {
}
