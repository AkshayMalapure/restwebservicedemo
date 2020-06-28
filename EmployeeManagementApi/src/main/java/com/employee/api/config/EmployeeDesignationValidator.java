package com.employee.api.config;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmployeeDesignationValidator implements ConstraintValidator<EmployeeDesignationConstraint,String>{
	
@Override
public boolean isValid(String employeeDesignation, ConstraintValidatorContext context) {
	
	
	if(employeeDesignation!=null && checkDesignation(employeeDesignation))
	{
		return true;
	}
	
	return false;
}

private boolean checkDesignation(String value) {


	Set<String> employeeDesignationSet=new LinkedHashSet<String>();
	employeeDesignationSet.add("Software Developer");
	employeeDesignationSet.add("DBA");
	employeeDesignationSet.add("Human Resource");
	employeeDesignationSet.add("Test Engineer");
	
	
	if(employeeDesignationSet.contains(value))
	{
		return true;
	}
	return false; 
}

}