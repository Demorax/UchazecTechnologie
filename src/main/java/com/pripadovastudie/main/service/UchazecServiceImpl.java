package com.pripadovastudie.main.service;

import com.pripadovastudie.main.model.Uchazec;
import com.pripadovastudie.main.repository.UchazecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UchazecServiceImpl implements UchazecService {

    @Autowired
    private UchazecRepository uchazecRepository;


    @Override
    public void addUchazec(Uchazec uchazec) {
        this.uchazecRepository.save(uchazec);
    }

    @Override
    public void deleteUchazec(int id) {
        this.uchazecRepository.deleteById(id);
    }


    @Override
    public Uchazec findById(int id){
        return this.uchazecRepository.findById(id).orElse(null);
    }
    @Override
    public List<Uchazec> getAllUchazec() {
        return this.uchazecRepository.findAll();
    }
}
