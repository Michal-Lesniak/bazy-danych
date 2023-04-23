package com.db.DBProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(unique = true)
    private Integer repairCode;

    private boolean status = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Appliance appliance;

    @ManyToMany
    private List<Part> part = new ArrayList<>();

    @OneToOne(mappedBy = "repair", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Dates dates;
}
