package com.employee.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.employee.api.model.Employee;

public interface EmployeeService {

	  Employee insertEmployee(Employee emp) throws MethodArgumentNotValidException;
	  Employee getEmployeeById(int employeeId) throws NoSuchElementException;
	  List<Employee> getAllEmployee() throws NoSuchElementException;
	  ResponseEntity<?> deleteEmployee(int employeeId) throws NoSuchElementException;
	  ResponseEntity<?> updateEmployee(int employeeId,Employee emp) throws MethodArgumentNotValidException;
	  
	  
	 
	  
}
