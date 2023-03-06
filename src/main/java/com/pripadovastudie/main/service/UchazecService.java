package com.pripadovastudie.main.service;

import com.pripadovastudie.main.model.Uchazec;

import java.util.List;

public interface UchazecService {

    void addUchazec(Uchazec uchazec);

    void deleteUchazec(int id);

    Uchazec findById(int id);

    List<Uchazec> getAllUchazec();
}
