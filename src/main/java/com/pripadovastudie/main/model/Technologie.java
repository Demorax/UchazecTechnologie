package com.pripadovastudie.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalIdCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
public class Technologie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int technologieID;
    private String poznamka;

    @OneToMany(mappedBy = "technologie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Uchazec_Technologie> uchazec_technologies;


    public Technologie() {
    }

    public Technologie(String poznamka) {
        this.poznamka = poznamka;
    }

    public int getId() {
        return technologieID;
    }

    public void setId(int id) {
        this.technologieID = id;
    }

    public String getPoznamka() {
        return poznamka;
    }

    public void setPoznamka(String poznamka) {
        this.poznamka = poznamka;
    }

    public Collection<Uchazec_Technologie> getUchazec_technologies() {
        return uchazec_technologies;
    }

    public void setUchazec_technologies(Collection<Uchazec_Technologie> uchazec_technologies) {
        this.uchazec_technologies = uchazec_technologies;
    }

}
