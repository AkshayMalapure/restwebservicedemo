package com.employee.api.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.convert.converter.Converter;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.api.entity.EmployeeEntity;
import com.employee.api.model.Employee;

/**
 * Test class for model mapper
 * @author akshay
 *
 */
@RunWith(SpringRunner.class)
public class TestModelMapper {

	@Mock
	private Converter<EmployeeEntity,Employee> converter;
	
	@Mock
	private EmployeeModelMapper modelMapper;
	
	@Test
	public void testModelMapper()
	{
	
		Mockito.when(modelMapper.convert(setTestEntityData())).thenReturn(setTestModelData());
		
		Employee emp=modelMapper.convert(setTestEntityData());
		
		assertNotNull(emp);
		assertEquals(emp,setTestModelData()); 
		assertEquals(emp.getEmployeeAge(),setTestModelData().getEmployeeAge());
	}
	
	private EmployeeEntity setTestEntityData() {
		return new EmployeeEntity(1, "Akshay Malapure", 23, "Software Developer", "akshaymalapure@gmail.com", 60000);
	}
	
	private Employee setTestModelData() {
		return new Employee(1, "Akshay Malapure", 23, "Software Developer", "akshaymalapure@gmail.com", 60000);
	}

}
