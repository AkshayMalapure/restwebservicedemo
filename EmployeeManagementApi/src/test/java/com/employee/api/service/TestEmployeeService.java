package com.employee.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.employee.api.entity.EmployeeEntity;
import com.employee.api.model.Employee;
import com.employee.api.repository.EmployeeRepository;

/**
 * Test class for Employee Service
 * @author akshay
 *
 */
@RunWith(SpringRunner.class)
public class TestEmployeeService {

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@Spy
	private ConversionService conversionService;

	@Test
	public void testAddEmployee() throws MethodArgumentNotValidException {

		Mockito.when(conversionService.convert(setTestModelData(), EmployeeEntity.class))
				.thenReturn(setTestEntityData());

		Mockito.when(conversionService.convert(setTestEntityData(), Employee.class)).thenReturn(setTestModelData());

		Mockito.when(employeeRepository.save(setTestEntityData())).thenReturn(setTestEntityData());

		Employee emp = employeeService.insertEmployee(setTestModelData());

		assertEquals(emp, setTestModelData());

	}
	
	
	@Test
	public void testGetEmployee()
	{
		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(setTestEntityData()));
		
		
		Mockito.when(conversionService.convert(setTestEntityData(), Employee.class)).thenReturn(setTestModelData());
		
		
		
		
		Employee emp = employeeService.getEmployeeById(1);
		assertEquals(emp.getEmployeeAge(),setTestEntityData().getEmployeeAge());
	}	
	
	
	@Test(expected=NoSuchElementException.class)
	public void testGetEmployeeInvalidId()
	{
		Mockito.when(employeeRepository.findById(2)).thenReturn(Optional.empty());
		Mockito.when(!employeeRepository.findById(2).isPresent()).thenThrow((new NoSuchElementException()));
		
		Mockito.when(employeeRepository.findById(2)).thenThrow(new NoSuchElementException());
		
		
	
		Employee emp = employeeService.getEmployeeById(2);
		
		
	}	
		

	@Test
	public void testGetEmployeeDetailList() {
		// Using Arrays.asList saves memory
		List<EmployeeEntity> entityList = Arrays.asList(setTestEntityData(), setTestEntityData2());
		Mockito.doReturn(entityList).when(employeeRepository).findAll();

		List<Employee> modelist = employeeService.getAllEmployee();

		assertFalse(modelist.isEmpty());
		assertTrue(modelist.size() > 0);

	}

	@Test(expected = NoSuchElementException.class)
	public void testGetEmployeeDetailList_WhenValidDataNotPresent_ExpectNoSuchElementException() {
		// Using Arrays.asList saves memory

		Mockito.when(employeeRepository.findAll()).thenThrow(new NoSuchElementException("No data found"));
		
		List<Employee> modelist = employeeService.getAllEmployee();
		 
		 

	}

	@Test
	public void testDeleteEmployeeById() {
		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(setTestEntityData()));

		ResponseEntity<?> result = employeeService.deleteEmployee(1);
		assertNotNull(result);

	}
	
	@Test(expected = NoSuchElementException.class)
	public void testDeleteEmployeeByIdInvalid() {
		Mockito.when(employeeRepository.findById(2)).thenThrow(new NoSuchElementException("No Data Found"));
		

		ResponseEntity<?> result = employeeService.deleteEmployee(2);
		assertTrue(result.getStatusCode().equals(HttpStatus.NO_CONTENT));

	}
	

	@Test
	public void testUpdateEmployee() throws MethodArgumentNotValidException {
		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(setTestEntityData()));

		EmployeeEntity entity = employeeRepository.findById(1).get();

		entity.setEmployeeSalary(70000);
		Mockito.when(employeeRepository.save(entity)).thenReturn(entity);

		Employee emp = setTestModelData();
		emp.setEmployeeSalary(70000);

		Mockito.when(conversionService.convert(entity, Employee.class)).thenReturn(emp);

		assertNotNull(employeeService.updateEmployee(1, emp));
		assertTrue(employeeService.updateEmployee(1, emp).getStatusCode().equals(HttpStatus.OK));

	}

	private Employee setTestModelData() {
		return new Employee(1, "Akshay Malapure", 23, "Software Developer", "akshaymalapure@gmail.com", 60000);
	}

	private EmployeeEntity setTestEntityData() {
		return new EmployeeEntity(1, "Akshay Malapure", 23, "Software Developer", "akshaymalapure@gmail.com", 60000);
	}

	private EmployeeEntity setTestEntityData2() {
		return new EmployeeEntity(2, "John Smith ", 24, "Software Developer", "johnsmith @gmail.com", 60000);
	}
}
