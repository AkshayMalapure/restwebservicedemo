package com.employee.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.employee.api.entity.EmployeeEntity;
import com.employee.api.model.Employee;
import com.employee.api.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Employee Service layer provides business logic for operations
 * @author akshay
 *
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ConversionService conversionService;

	/**This method is used for creation of employee record 
	 * @param emp
	 * @return Employee 
	 * 
	 */
	@Override
	public Employee insertEmployee(Employee emp) throws MethodArgumentNotValidException {

		log.info("Inside service layer for creation of employee record");
		EmployeeEntity entity=conversionService.convert(emp, EmployeeEntity.class);

		return conversionService.convert(employeeRepository.save(entity),
				Employee.class);
	}

	
	/**This method is used for reading employee record on basis of id
	 * @param employeeId 
	 * @return Employee 
	 * @throws NoSuchElementException if record doesnt exist with particular id for reading
	 */
	@Override
	public Employee getEmployeeById(int employeeId) throws NoSuchElementException{
		
		
		log.info("Inside Service Layer Get Employee Request for id {}:",employeeId);
		if(!employeeRepository.findById(employeeId).isPresent())
		{
			throw new NoSuchElementException("No data found for id:"+employeeId); 
		}

		return conversionService.convert(employeeRepository.findById(employeeId).get(), Employee.class);
	}

	/**This method is used for reading employee record on basis of id
	 * 
	 * @return List of Employee Records
	 * @throws NoSuchElementException if record doesnt exist with particular id for reading
	 */
	@Override
	public List<Employee> getAllEmployee() throws NoSuchElementException{
		
		log.info("Inside service layer for reading list  of employee records");
		if(employeeRepository.findAll().isEmpty()){
			throw new NoSuchElementException("No data found");
		}

		List<Employee> employeeModelList=employeeRepository.findAll().stream().map(s-> conversionService.convert(s,Employee.class)).collect(Collectors.toList());
		
		return employeeModelList;
	}

	/**This method is used for deletion of employee record on basis of employee id
	 * @param employeeId
	 * @return Http status ok for successful deletion else provides status as no content
	 * @throws NoSuchElementException if record doesnt exist with particular id for deletion
	 */
	@Override
	public ResponseEntity<Object> deleteEmployee(int employeeId)throws NoSuchElementException {
		log.info("Inside service layer for deletion of employee record for id {} :",employeeId);
		
		if(employeeRepository.findById(employeeId).isPresent()) {
			employeeRepository.deleteById(employeeId);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	/**This endpoint/method is used for updation of employee record 
	 * @param employeeId
	 * @param Employee emp
	 * @return  Http status ok for successful updation  else provides status as no content
	 * @throws MethodArgumentNotValidException if fields doesnt satisfy validation constraints.
	 */
	@Override
	public ResponseEntity<Object> updateEmployee(int employeeId,Employee emp) {
		
		log.info("Inside service layer for updation of employee record for id {} :",employeeId);
		if(employeeRepository.findById(employeeId).isPresent())
		{
			EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();
			employeeEntity.setEmployeeName(emp.getEmployeeName());
			employeeEntity.setEmployeeAge(emp.getEmployeeAge());
			employeeEntity.setEmployeeEmailId(emp.getEmployeeEmailId());
			employeeEntity.setEmployeeSalary(emp.getEmployeeSalary());
			employeeEntity.setEmployeeDesignation(emp.getEmployeeDesignation());
			
			employeeRepository.save(employeeEntity);
			return new ResponseEntity<Object>(HttpStatus.OK);
			
			
		}
		
		 return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
