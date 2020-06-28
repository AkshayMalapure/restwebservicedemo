package com.employee.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.api.model.Employee;
import com.employee.api.service.EmployeeServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akshay
 * Employee Controller serves web request providing 4 operations creating,reading,updating,deleting employee records
 *
 */
@Slf4j 
@RestController
@RequestMapping(path="employee-management-api/v1/")
public class EmployeeController {

	
	@Autowired
	private  EmployeeServiceImpl empServiceImpl;
	
	
	/**
	 * The endpoint.method accepts employee id and returns employee record in response
	 * @param employeeId
	 * @return Employee 
	 * @throws NoSuchElementException for invalid employee id
	 */
	
	 /*Naming convention of api should not expose underneath operation
	  * Example {id} is good
	  * You should not use getEmployee in api
	  * */
	@GetMapping(path="{id}")
	public Employee getEmployeeById(@PathVariable("id") int employeeId ) throws NoSuchElementException
	{
		log.info("Inside Controller Get Employee Request for id {}",employeeId);
		return empServiceImpl.getEmployeeById(employeeId);
	}
	
	
	/**
	 * This endpoint/method accepts no argument and returns list of employee records
	 * @return
	 * @throws NoSuchElementException if no data present in database
	 */
	@GetMapping(path="employees")
	public List<Employee> getAllEmployees() throws NoSuchElementException
	{
		log.info("Inside Controller Client Requested all records of employee");
		return empServiceImpl.getAllEmployee();
	}
	
	/**
	 * This endpoint/method is used for insertion/creation of employee record
	 * @param emp
	 * @return
	 * @throws MethodArgumentNotValidException if valid fields are not provided in request
	 */
	
	/*@Valid is used for validating fields in request*/
	@PostMapping(path="employee")
	public Employee insertEmployeeRecord(@RequestBody @Valid Employee emp) throws MethodArgumentNotValidException
	{
		log.info("Inside Controller Client Requested creating of employee record");
		return empServiceImpl.insertEmployee(emp);
		
	}
	
	/**This endpoint/method is used for deletion of employee record on basis of employee id
	 * @param id
	 * @return Http status ok for successful deletion else provides status as no content
	 * @throws NoSuchElementException if record doesnt exist with particular id for deletion
	 */
	@DeleteMapping(path="{id}")
	public ResponseEntity<?> deleteEmployeeRecord(@PathVariable ("id") int employeeId) throws NoSuchElementException
	{
		log.info("Inside Controller Client Requested Deletion of employee record for id {}:",employeeId);
		return empServiceImpl.deleteEmployee(employeeId);
	}
	
	/**This endpoint/method is used for updation of employee record 
	 * @param employeeId
	 * @param  emp
	 * @return  Http status ok for successful updation  else provides status as no content
	 * @throws MethodArgumentNotValidException if fields doesnt satisfy validation constraints.
	 */
	@PutMapping(path="{id}")
	public ResponseEntity<?> updateEmployeeRecord(@PathVariable ("id") int employeeId,@RequestBody @Valid Employee emp) throws MethodArgumentNotValidException
	{
		log.info("Inside Controller Client Requested updation of employee record for id {} :",employeeId);
		return empServiceImpl.updateEmployee(employeeId,emp);
	}
	
	
	
	
}
