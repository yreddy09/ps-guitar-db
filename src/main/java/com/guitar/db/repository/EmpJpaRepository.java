package com.guitar.db.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guitar.db.model.Emp;

@Repository
public interface EmpJpaRepository extends JpaRepository<Emp, BigDecimal> {

}
