package com.pripadovastudie.main.repository;

import com.pripadovastudie.main.model.Uchazec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UchazecRepository extends JpaRepository<Uchazec, Integer> {
}
