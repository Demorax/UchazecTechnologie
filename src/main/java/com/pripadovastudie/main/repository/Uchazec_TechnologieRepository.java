package com.pripadovastudie.main.repository;

import com.pripadovastudie.main.model.Uchazec_Technologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Uchazec_TechnologieRepository extends JpaRepository<Uchazec_Technologie, Integer> {
}
