package com.example.enterdataservice.repository;

import com.example.enterdataservice.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

}
