package com.pripadovastudie.main.service;

import com.pripadovastudie.main.model.Uchazec_Technologie;
import com.pripadovastudie.main.repository.Uchazec_TechnologieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Uchazec_TechnologieServiceImpl implements Uchazec_TechnologieService {

    @Autowired
    private Uchazec_TechnologieRepository uchazecTechnologieRepository;

    @Override
    public void addUchazTechno(Uchazec_Technologie uchazec_technologie) {
        this.uchazecTechnologieRepository.save(uchazec_technologie);
    }

    @Override
    public void deleteUchazTechno(int id) {
        this.uchazecTechnologieRepository.deleteById(id);
    }


    @Override
    public Uchazec_Technologie findById(int id) {
        return this.uchazecTechnologieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Uchazec_Technologie> getAllUchazTechnp() {
        return this.uchazecTechnologieRepository.findAll();
    }
}
