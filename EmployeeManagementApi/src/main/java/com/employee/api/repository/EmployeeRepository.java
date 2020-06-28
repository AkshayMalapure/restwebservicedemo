package com.employee.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.api.entity.EmployeeEntity;

/**
 * Repository layer for employee 
 * @author akshay
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer>{

}
