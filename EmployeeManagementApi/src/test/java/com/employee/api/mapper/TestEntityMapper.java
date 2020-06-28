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
 * @author akshay
 *Test class for entity mapper
 */
@RunWith(SpringRunner.class)
public class TestEntityMapper {

	@Mock
	private Converter<EmployeeEntity,Employee> converter;
	
	@Mock
	private EmployeeEntityMapper entityMapper;
	
	@Test
	public void testEntityMapper()
	{
	
		Mockito.when(entityMapper.convert(setTestModelData())).thenReturn(setTestEntityData());
		
		EmployeeEntity emp=entityMapper.convert(setTestModelData());
		
		assertNotNull(emp);
		assertEquals(emp,setTestEntityData()); 
		assertEquals(emp.getEmployeeAge(),setTestEntityData().getEmployeeAge());
	}
	
	private EmployeeEntity setTestEntityData() {
		return new EmployeeEntity(1, "Akshay Malapure", 23, "Software Developer", "akshaymalapure@gmail.com", 60000);
	}
	
	private Employee setTestModelData() {
		return new Employee(1, "Akshay Malapure", 23, "Software Developer", "akshaymalapure@gmail.com", 60000);
	}

}

	

