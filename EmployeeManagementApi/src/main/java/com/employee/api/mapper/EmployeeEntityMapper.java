package com.employee.api.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.employee.api.entity.EmployeeEntity;
import com.employee.api.model.Employee;

import lombok.Data;

/**
 * Mapper for Coversion of Employee Model to Employee Entity
 * @author akshay
 *
 */
@Component
@Data
public class EmployeeEntityMapper implements Converter<Employee, EmployeeEntity> {

	/**
	 *This method converts Employee model to employee entity
	 *@param Employee Model object
	 *@return Employee entity object
	 */
	@Override
	public EmployeeEntity convert(Employee employee) {
		
		return new EmployeeEntity(employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeAge(),
				employee.getEmployeeDesignation(), employee.getEmployeeEmailId(), employee.getEmployeeSalary());
	}

}
