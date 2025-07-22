package com.centime.processor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centime.processor.model.Request;

import jakarta.validation.Valid;

/**
 * REST Controller for Service 3 - Processor Service
 * 
 * As specified in requirements: "This exposes one post method which is called
 * by first service to print/log the passed json and return the concatenated
 * name elements as a string (example - 'John Doe')"
 */
@RestController
@RequestMapping("/api")
public class ProcessorController {

	private static final Logger logger = LoggerFactory.getLogger(ProcessorController.class);

	/**
	 * Health check endpoint
	 */
	@GetMapping("/health")
	public ResponseEntity<String> health() {
		logger.info("Service 3 health check called");
		return ResponseEntity.ok("Up");
	}

	/**
	 * Main processing endpoint
	 * 
	 * As specified in requirements: "one post method which is called by first
	 * service to print/log the passed json and return the concatenated name
	 * elements as a string (example - 'John Doe')"
	 * 
	 * This method: 1. Receives the same JSON payload from Service 1 2. Logs/prints
	 * the passed JSON as required 3. Returns concatenated name elements as a string
	 */
	@PostMapping("/process")
	public ResponseEntity<String> processUser(@Valid @RequestBody Request userRequest,
			@RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

		if (traceId != null) {
			MDC.put("traceId", traceId);
		}

		logger.info("[TraceID: {}] POST /api/process - Processing request received", traceId);

		// Print/log the passed JSON as specified in requirements
		logger.info("[TraceID: {}] POST /api/process - User data: {}", traceId, userRequest);

		try {
			// Return concatenated name elements as a string (example - "John Doe")
			String concatenatedName = userRequest.getName() + " " + userRequest.getSurname();

			logger.info("[TraceID: {}] POST /api/process - Processed result: {}", traceId, concatenatedName);

			return ResponseEntity.ok(concatenatedName);

		} catch (Exception e) {
			logger.error("[TraceID: {}] POST /api/process - Error processing user data: {}", traceId, e.getMessage());
			throw e;
		} finally {
			MDC.clear();
		}
	}

	/**
	 * Simple test endpoint to verify service is working
	 */
	@GetMapping("/test")
	public ResponseEntity<String> test() {
		logger.info("Service 3 test endpoint called");
		return ResponseEntity.ok("Service 3 is processing data correctly!");
	}
}
