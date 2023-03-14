package com.db.DBProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private Integer userCode;

    @NotNull
    private String name;

    private String email;

    @NotNull
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Repair> repair = new ArrayList<>();
}
