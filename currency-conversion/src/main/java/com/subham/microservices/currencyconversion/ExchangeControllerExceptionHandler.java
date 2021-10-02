package com.subham.microservices.currencyconversion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExchangeControllerExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException runtimeException) {
		return ResponseEntity.badRequest().body(runtimeException.getMessage());
	}
}
