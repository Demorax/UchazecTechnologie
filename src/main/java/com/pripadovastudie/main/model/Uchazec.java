package com.pripadovastudie.main.model;

import jakarta.persistence.*;
import org.apache.el.stream.Stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Uchazec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uchazecID;
    private String jmeno;
    private String prijmeni;

    @OneToMany(mappedBy = "uchazec", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Uchazec_Technologie> uchazec_technologie;
    public Uchazec() {
    }

    public Uchazec(String jmeno, String prijmeni) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
    }

/*    public Uchazec(String jmeno, String prijmeni, Uchazec_Technologie... uchazec_technologie) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        for (Uchazec_Technologie p : uchazec_technologie) p.setUchazec(this);
        this.uchazec_technologie = List.of(uchazec_technologie);
    }*/

    public int getId() {
        return uchazecID;
    }

    public void setId(int id) {
        this.uchazecID = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public Collection<Uchazec_Technologie> getUchazec_technologie() {
        return uchazec_technologie;
    }

    public void setUchazec_technologie(Collection<Uchazec_Technologie> uchazec_technologie) {
        this.uchazec_technologie = uchazec_technologie;
    }
}
