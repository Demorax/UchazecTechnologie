package com.pripadovastudie.main.service;

import com.pripadovastudie.main.model.Uchazec_Technologie;

import java.util.List;

public interface Uchazec_TechnologieService {
    void addUchazTechno(Uchazec_Technologie uchazec_technologie);

    void deleteUchazTechno(int id);


    Uchazec_Technologie findById(int id);


    List<Uchazec_Technologie> getAllUchazTechnp();
}
