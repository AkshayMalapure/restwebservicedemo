package com.employee.api.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.employee.api.model.Employee;
import com.employee.api.service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test class for Employee Controller
 * 
 * @author akshay
 *
 */
@RunWith(SpringRunner.class)
// spring test slicing focus only on testing controller
@WebMvcTest(value = EmployeeController.class)
public class TestEmployeeController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeServiceImpl employeeServiceImpl;

	@InjectMocks
	private EmployeeController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	/*
	 * Naming convention used for test 
		First Part           Second Part           Third Part
	 * testGetEmployee_WhenInvalidIdSent_ExpectBadRequest
	 * 
	 * 1)First Part indicates what you are testing ex 2)Second part indicates
	 * condition 3)Last Part indicates expected result /
	 */

	public void testGetEmployee_WhenInvalidIdSent_ExpectBadRequest() throws Exception {
		mockMvc.perform(get(("/employee-management-api/v1/1.1")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testGetEmployee_WhenValidIdSent_ExpectSuccessfulResponse() throws Exception {
		Mockito.when(employeeServiceImpl.getEmployeeById(1)).thenReturn(getMockData());

		mockMvc.perform(get(("/employee-management-api/v1/1")).contentType(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk());

	}

	@Test
	public void testGetAllEmployee_WhenDataPresent_ExpectSuccessfulResponse() throws Exception {
		Mockito.when(employeeServiceImpl.getAllEmployee()).thenReturn(Arrays.asList(getMockData(), getMockData()));

		mockMvc.perform(get(("/employee-management-api/v1/employees")).contentType(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk());

	}

	@Test
	public void testAddtEmployee_WhenValidRecordSent_ExpectSuccessfulResponse() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(getMockData());

		Mockito.when(employeeServiceImpl.insertEmployee(getMockData())).thenReturn(getMockData());

		mockMvc.perform(
				post(("/employee-management-api/v1/employee")).contentType(MediaType.APPLICATION_JSON).content(json))

				.andExpect(status().isOk());
	}

	@Test
	public void testAddtEmployee_WhenInValidRecordSent_ExpectBadRequest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(getInvalidTestData());

		Mockito.when(employeeServiceImpl.insertEmployee(getInvalidTestData()))
				.thenThrow(MethodArgumentNotValidException.class);

		mockMvc.perform(
				post(("/employee-management-api/v1/employee")).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void testDeleteEmployee_WhenvalidIdSent_ExpectSuccessfullResponse() throws Exception {
		mockMvc.perform(delete(("/employee-management-api/v1/1")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void testUpdateEmployee_WhenValidRecordSent_ExpectSuccessfulResponse() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		Employee updateEmp = getMockData();
		updateEmp.setEmployeeSalary(60000);

		String json = mapper.writeValueAsString(updateEmp);

		ResponseEntity response = new ResponseEntity(HttpStatus.OK);

		Mockito.when(employeeServiceImpl.updateEmployee(1, updateEmp)).thenReturn(response);

		mockMvc.perform(put(("/employee-management-api/v1/1")).contentType(MediaType.APPLICATION_JSON).content(json))

				.andExpect(status().isOk());

		ResponseEntity<?> result = controller.updateEmployeeRecord(1, updateEmp);
		assertNotNull(result);
		assertEquals(result.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void testUpdateEmployee_WhenInValidRecordSent_ExpectSuccessfulResponse() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		String json = mapper.writeValueAsString(getMockData());

		ResponseEntity response = new ResponseEntity(HttpStatus.NO_CONTENT);

		Mockito.when(employeeServiceImpl.updateEmployee(2, getMockData())).thenReturn(response);

		mockMvc.perform(put(("/employee-management-api/v1/2")).contentType(MediaType.APPLICATION_JSON).content(json))

				.andExpect(status().isNoContent());

		ResponseEntity<?> result = controller.updateEmployeeRecord(2, getMockData());
		assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);

	}

	private Employee getMockData() {
		return new Employee(1, "Akshay Malapure", 23, "Software Developer", "akshaymalapure@gmail.com", 50000);
	}

	private Employee getInvalidTestData() {
		return new Employee(1, "Akshay Malapure", -23, "Software Developer", "akshaymalapure@gmail.com", 50000);
	}

}
