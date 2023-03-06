package com.pripadovastudie.main.repository;

import com.pripadovastudie.main.model.Technologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologieRepository extends JpaRepository<Technologie, Integer> {
}
