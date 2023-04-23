package com.db.DBProject.dto;

public record PartDto(Integer partCode,
                      String name,
                      Integer price,
                      Integer applianceCode,
                      String photoURL) {
}
