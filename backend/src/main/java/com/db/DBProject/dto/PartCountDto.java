package com.db.DBProject.dto;

public record PartCountDto(Integer partCode,
                           String name,
                           Integer price,
                           Integer applianceCode,
                           String photoURL,
                           Integer amount) {
}
