package com.pripadovastudie.main.service;

import com.pripadovastudie.main.model.Technologie;
import com.pripadovastudie.main.repository.TechnologieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologieServiceImpl implements TechnologieService {

    @Autowired
    private TechnologieRepository technologieRepository;

    @Override
    public void addTechnologie(Technologie technologie) {
        this.technologieRepository.save(technologie);
    }

    @Override
    public void deleteTechnologie(int id) {
        this.technologieRepository.deleteById(id);
    }


    @Override
    public Technologie findById(int id){
        return this.technologieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Technologie> getAllTechnologie() {
        return this.technologieRepository.findAll();
    }
}
