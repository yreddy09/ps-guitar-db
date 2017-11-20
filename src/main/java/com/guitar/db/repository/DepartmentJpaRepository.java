package com.guitar.db.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.Department;

public interface DepartmentJpaRepository extends JpaRepository<Department, BigDecimal> {

}
