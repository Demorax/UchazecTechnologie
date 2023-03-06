package com.pripadovastudie.main.service;

import com.pripadovastudie.main.model.Technologie;

import java.util.List;

public interface TechnologieService {
    void addTechnologie(Technologie technologie);

    void deleteTechnologie(int id);

    Technologie findById(int id);

    List<Technologie> getAllTechnologie();
}
