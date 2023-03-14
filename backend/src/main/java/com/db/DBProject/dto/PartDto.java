package com.db.DBProject.dto;

public record PartDto(Integer partCode,
                      String name,
                      Integer cost,
                      Integer applianceCode,
                      String photoURL) {
}
