package com.db.DBProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Dates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private Date registerDate;

    private Date finishDate;

    private Date collectionDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repair_id")
    private Repair repair;
}
