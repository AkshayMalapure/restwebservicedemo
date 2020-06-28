package com.employee.api.model;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ErrorResponse {

	@JsonInclude(Include.NON_NULL)
	private HttpStatus status;
	
	@JsonInclude(Include.NON_NULL)
	private Date timestamp;
	
	@JsonInclude(Include.NON_NULL)
	private String message;
	
	@JsonInclude(Include.NON_NULL)
	private String detail;
	
	@JsonInclude(Include.NON_NULL)
	private List<String> details;
}
