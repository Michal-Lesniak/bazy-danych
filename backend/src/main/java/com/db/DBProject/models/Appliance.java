package com.db.DBProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(unique = true)
    private Integer applianceCode;

    @NotNull
    private String name;

    private String photoURL;

    @OneToMany(
            mappedBy = "appliance",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Part> part = new ArrayList<>();
}
