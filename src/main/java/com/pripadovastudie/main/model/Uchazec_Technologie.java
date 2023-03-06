package com.pripadovastudie.main.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity

@Table(name = "uchazec_technologie")
public class Uchazec_Technologie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uchazec_technlogieID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "uchazecID")
    private Uchazec uchazec;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "technologieID")
    private Technologie technologie;

    @Column(name = "hodnota")
    private int hodnota;

    @Column(name = "poznamka")
    private String poznamka;

    ////ted
    public Uchazec_Technologie(Technologie technologie, int hodnota) {
        this.technologie = technologie;
        this.hodnota = hodnota;
    }

    public Uchazec_Technologie() {

    }

    public Uchazec_Technologie(Uchazec uchazec, Technologie technologie, int hodnota, String poznamka) {
        this.uchazec = uchazec;
        this.technologie = technologie;
        this.hodnota = hodnota;
        this.poznamka = poznamka;
    }

    public int getUchazec_technlogieID() {
        return uchazec_technlogieID;
    }

    public void setUchazec_technlogieID(int uchazec_technlogieID) {
        this.uchazec_technlogieID = uchazec_technlogieID;
    }

    public Uchazec getUchazec() {
        return uchazec;
    }

    public void setUchazec(Uchazec uchazec) {
        this.uchazec = uchazec;
    }

    public Technologie getTechnologie() {
        return technologie;
    }

    public void setTechnologie(Technologie technologie) {
        this.technologie = technologie;
    }

    public int getHodnota() {
        return hodnota;
    }

    public void setHodnota(int hodnota) {
        this.hodnota = hodnota;
    }

    public String getPoznamka() {
        return poznamka;
    }

    public void setPoznamka(String poznamka) {
        this.poznamka = poznamka;
    }
}


