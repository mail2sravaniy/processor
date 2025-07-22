package com.centime.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Service 3 - Processor Application
 * 
 * Processing service that receives user data and returns concatenated names.
 * 
 * As specified in requirements: "This exposes one post method which is called
 * by first service to print/log the passed json and return the concatenated
 * name elements as a string (example - 'John Doe')"
 */
@SpringBootApplication
public class ProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);

		System.out.println("");
		System.out.println("⚙️  Service 3 (Processor) started successfully!");
		System.out.println("🌐 Server: http://localhost:8083");
		System.out.println("🏥 Health Check: http://localhost:8083/api/health");
		System.out.println("⚙️  Processing API: http://localhost:8083/api/process");
		System.out.println("🔗 Integration: Called by Service 1 for user data processing");
		System.out.println("");
	}
}
