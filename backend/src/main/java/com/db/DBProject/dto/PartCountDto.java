package com.db.DBProject.dto;

public record PartCountDto(Integer partCode,
                           String name,
                           Integer cost,
                           Integer applianceCode,
                           String photoURL,
                           Integer count) {
}
