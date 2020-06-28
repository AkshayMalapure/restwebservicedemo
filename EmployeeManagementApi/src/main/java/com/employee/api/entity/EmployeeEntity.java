package com.employee.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeId;

	@Column(name = "Employee_Name", nullable = false)
	private String employeeName;

	@Column(name = "Employee_Age", nullable = false)
	private int employeeAge;

	@Column(name = "Employee_Designation", nullable = false)
	private String employeeDesignation;

	@Column(name = "Employee_EmailId", nullable = false)
	private String employeeEmailId;
	
	@Column(name = "Employee_Salary",nullable=false)
	private long employeeSalary;
	
	
	
	
	
	

}
