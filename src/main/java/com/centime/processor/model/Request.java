package com.centime.processor.model;

import jakarta.validation.constraints.NotBlank;

/**
 * Request model for user data in Service 3
 * Must match the structure sent by Service 1
 * 
 * This model represents the JSON structure:
 * {
 *   "name": "John",
 *   "surname": "Doe"
 * }
 */
public class Request {
    
    @NotBlank(message = "Name cannot be empty")
    private String name;
    
    @NotBlank(message = "Surname cannot be empty")
    private String surname;
    
    // Default constructor
    public Request() {}
    
    // Constructor with parameters
    public Request(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    
    // Getters and Setters
    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public String getSurname() { 
        return surname; 
    }
    
    public void setSurname(String surname) { 
        this.surname = surname; 
    }
    
    /**
     * Override toString for better logging output
     * This will be used when logging the received JSON data
     */
    @Override
    public String toString() {
        return "UserRequest{name='" + name + "', surname='" + surname + "'}";
    }
}

