# Service 3 - Processor Microservice

## 🎯 Overview
Service 3 is a processing service that receives user data (Name and Surname) via POST request, logs the received JSON data, and returns the concatenated full name. It demonstrates input validation, JSON processing, and JavaEE log tracing.

## ✨ Features
- ✅ **JSON Processing** - Receives and processes user data via POST
- ✅ **Input Validation** - Validates required fields with meaningful error messages
- ✅ **JSON Logging** - Logs all received JSON data as specified in requirements
- ✅ **JavaEE Log Tracing** - Supports trace ID propagation and logging
- ✅ **Error Handling** - Comprehensive exception handling and validation
- ✅ **Spring Boot 3.2.0** - Modern Spring Boot implementation

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build and Run
```bash
# Make scripts executable
chmod +x *.sh

# Build the service
./build.sh

# Run the service
./run.sh
```

### Test the Service
```bash
# Run comprehensive tests
./test.sh

# Manual test
curl -X POST http://localhost:8083/api/process \
  -H "Content-Type: application/json" \
  -d '{"name": "John", "surname": "Doe"}'
```

## 📋 API Endpoints

### Health Check
```http
GET /api/health
```
**Response:** `"Up"`

### Process User Data
```http
POST /api/process
Content-Type: application/json
Headers:
  X-Trace-Id: optional-trace-id-from-calling-service

{
  "name": "John",
  "surname": "Doe"
}
```
**Response:** `"John Doe"`

## 🏗️ Integration with Other Services
This service is called by Service 1 (Orchestrator) as part of the orchestration flow:

1. **Service 1** receives a client request with user data
2. **Service 1** calls Service 2 → Gets "Hello"
3. **Service 1** calls **Service 3** `POST /api/process` → Gets "John Doe"
4. **Service 1** combines: "Hello" + "John Doe" = "Hello John Doe"

## 🧪 Request/Response Examples

### Success Case
```bash
curl -X POST http://localhost:8083/api/process \
  -H "Content-Type: application/json" \
  -d '{"name": "Jane", "surname": "Smith"}'

# Response: "Jane Smith"
```

### Validation Error
```bash
curl -X POST http://localhost:8083/api/process \
  -H "Content-Type: application/json" \
  -d '{"name": "", "surname": ""}'

# Response: 400 Bad Request with validation errors
```

## 🔍 Configuration
Edit `src/main/resources/application.yml`:
```yaml
server:
  port: 8083
spring:
  application:
    name: service3-processor
```

## 🐳 Docker Usage
```bash
# Build Docker image
docker build -t service3-processor .

# Run with Docker Compose
docker-compose up -d
```

## 🔍 Logging
Service 3 logs all received JSON data and supports trace ID propagation:
```
# Example log output
[TraceID: abc-123] Service3 - POST /process called
[TraceID: abc-123] Service3 - User data: UserRequest{name='John', surname='Doe'}
[TraceID: abc-123] Service3 - Processed result: John Doe
```

## ❌ Error Handling
- **Validation Errors**: Returns 400 with field-specific error messages
- **Processing Errors**: Returns 500 with trace ID for debugging
- **Malformed JSON**: Returns 400 with parsing error details
