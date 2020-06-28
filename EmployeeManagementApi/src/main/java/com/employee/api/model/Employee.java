package com.employee.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.employee.api.config.EmployeeDesignationConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	

	@PositiveOrZero(message = "Employee id cannot be negative")
	private int employeeId;

	@NotEmpty(message = "Employee name cannot be empty")
	@NotBlank(message = "Employee name cannot be blank")
	@Pattern(regexp = "[^0-9!@#$^%&*()<>,;{}|.~?]*", message = "name contains invalid characters")
	private String employeeName;

	@Positive(message = "Employee age cannot be negative")
	@Min(value = 21, message = "minimum age of employee should be 21")
	@Max(value = 60, message = "maximum age of employee should not exceed 60")
	private int employeeAge;

	@NotEmpty(message = "Employee desigmation cannot be empty")
	@NotBlank(message = "Employee designation cannot be blank")
	@Pattern(regexp = "[^0-9!@#$^%&*()<>,;{}|.~?]*", message = "designation contains invalid characters")
	@EmployeeDesignationConstraint
	private String employeeDesignation;

	@NotEmpty(message = "Employee email id cannot be empty")
	@NotBlank(message = "Employee email id cannot be blank")
	@Email(regexp ="[^%&*()<>,;{}|~?]*" ,message="email id contains invalid characters") 
	private String  employeeEmailId;

	@Positive(message = "Employee salary cannot be negative")
	@Min(value = 25000, message = "minimum salary of employee should be 25000")
	@Max(value = 1000000, message = "maximum salary of employee should be 1000000")
	private long employeeSalary;

}