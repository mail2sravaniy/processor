package com.centime.processor.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for Service 3 Handles validation errors and runtime
 * exceptions with proper logging
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Handle validation errors (e.g., empty Name or Surname) This will be triggered
	 * when the JSON validation fails
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException ex) {
		String traceId = MDC.get("traceId");
		logger.error("[TraceID: {}] Service 3 validation error: {}", traceId, ex.getMessage());

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity.badRequest().body(errors);
	}

	/**
	 * Handle runtime exceptions
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGenericError(Exception ex) {
		String traceId = MDC.get("traceId");
		logger.error("[TraceID: {}] Service 3 error: {}", traceId, ex.getMessage());

		Map<String, String> error = new HashMap<>();
		error.put("error", "Service 3 processing failed: " + ex.getMessage());
		error.put("traceId", traceId);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
