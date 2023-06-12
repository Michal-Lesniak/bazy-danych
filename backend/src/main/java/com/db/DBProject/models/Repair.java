package com.db.DBProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
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

    @JsonManagedReference
    @NotNull
    @OneToMany(
        mappedBy = "repair",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<DateAction> dateActions = new ArrayList<>();

    public void addDateAction(DateAction dateAction){
        dateActions.add(dateAction);
        dateAction.setRepair(this);
    }

    public void removeDateAction(DateAction dateAction){
        dateActions.remove(dateAction);
        dateAction.setRepair(null);
    }

    public boolean getStatus() {
        return this.status;
    }
}
