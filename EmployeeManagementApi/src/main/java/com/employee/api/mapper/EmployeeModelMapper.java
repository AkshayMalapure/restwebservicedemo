package com.employee.api.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.employee.api.entity.EmployeeEntity;
import com.employee.api.model.Employee;

import lombok.Data;

/**
 * Employee Model mapper converts entity to model
 * @author akshay
 *
 */
@Component
@Data
public class EmployeeModelMapper implements Converter<EmployeeEntity, Employee> {

	/**
	 *This mehtod converts Employee entity to model
	 *@param Employee entity
	 *@return Employee model obect
	 */
	@Override
	public Employee convert(EmployeeEntity entity) {

		return new Employee(entity.getEmployeeId(), entity.getEmployeeName(), entity.getEmployeeAge(),
				entity.getEmployeeDesignation(), entity.getEmployeeEmailId(), entity.getEmployeeSalary());

	}

}
