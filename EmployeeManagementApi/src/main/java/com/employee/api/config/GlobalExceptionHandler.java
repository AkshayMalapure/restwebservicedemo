package com.employee.api.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.employee.api.model.ErrorResponse;

/**
 * GlobalException handler for EmployeeManagement Api
 * @author akshay
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * This Method handles NoSuchElementException
	 * @param ex
	 * @param request
	 * @return Response entity with appropriate details
	 */
	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<Object> handleRecordNotFoundException(NoSuchElementException ex, WebRequest request) {
		return handleExceptionInternal(ex,
				ErrorResponse.builder().status(HttpStatus.NOT_FOUND).timestamp(new Date())
						.detail(ex.getLocalizedMessage()).message("Record not found").build(),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);

	}

	/**
	 * This Method handles MethodArgumentNotValidException
	 * @param ex
	 * @param request
	 * @param headers
	 * @param status
	 * @return Response entity with appropriate details
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getObjectName() + ":" + error.getDefaultMessage());
		}

		return handleExceptionInternal(ex, ErrorResponse.builder().details(errors).build(), headers,
				HttpStatus.BAD_REQUEST, request);
	}

}
