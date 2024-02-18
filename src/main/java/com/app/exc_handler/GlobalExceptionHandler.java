package com.app.exc_handler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;


@RestControllerAdvice 
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlemethodArgumentNotValidException(MethodArgumentNotValidException e)
	{
		System.out.println("in method arg excp"+e);
		List<ObjectError>globalerrorsErrors=e.getGlobalErrors();
		Map<String , String> map1=globalerrorsErrors.stream()
				.collect(Collectors.toMap(ObjectError::getObjectName, ObjectError::getDefaultMessage));
		List<FieldError>fieldErrors=e.getFieldErrors();
		Map<String, String> map2 = fieldErrors.stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		map1.putAll(map2);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map1);
		
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiResponse handleResourceNotFoundException(
			ResourceNotFoundException e) {
		System.out.println("in res not found " + e);
		return new ApiResponse(e.getMessage());
	}	
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handleAnyException(RuntimeException e) {
		System.out.println("in catch-all " + e);
		e.printStackTrace();
		return new ApiResponse(e.getMessage());
	}
}
