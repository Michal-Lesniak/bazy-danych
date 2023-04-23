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
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(unique = true)
    private Integer partCode;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer amount = 0;

    private String photoURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JsonIgnore
    private Appliance appliance;

    @ManyToMany(mappedBy = "part")
    @JsonIgnore
    private List<Repair> repair = new ArrayList<>();
}
