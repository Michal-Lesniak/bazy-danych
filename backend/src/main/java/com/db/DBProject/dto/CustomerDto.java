package com.db.DBProject.dto;

public record CustomerDto(Integer userCode,
                          String name,
                          String email,
                          String phone) {
}
