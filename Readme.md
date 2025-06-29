# Bookstore API Automation Framework

## Objective

This framework automates testing of the [FastAPI Bookstore](https://github.com/JK-Technosoft-Limited/bookstore) API, covering:

- Full CRUD operations: create, read, update, delete books  
- Validation of HTTP status codes, response body, and headers  
- Both positive and negative test scenarios  
- Request chaining using dynamically generated book IDs  
- JWT-based authentication with token acquisition and injection  

The aim is to provide clear test coverage, maintainable code, and reliable results.

---

## Tech Stack

- Java  
- Maven  
- TestNG  
- RestAssured  

---

## Setup and Run Guide

### 1. Run the Bookstore API Locally

1. Clone the repository:  
   `git clone https://github.com/JK-Technosoft-Limited/bookstore.git`  
2. Navigate into the cloned directory  
3. Install required dependencies (ensure Python and FastAPI are installed)  
4. Start the API server using: uvicorn main:app --reload  
5. Access API documentation at: `http://127.0.0.1:8000/docs`

---

### 2. Execute the Test Suite

#### From Command Line

- Navigate to the root of the automation project  
- Run: `mvn clean test`

#### From Eclipse IDE

1. Import the Maven project  
2. Right-click `testng.xml` → **Run As** → **TestNG Suite**

---

## Testing Strategy

- **Positive Tests**: Perform all CRUD operations with valid input  
- **Negative Tests**: Test invalid payloads, missing required fields, unauthorized access  
- **Request Chaining**: Use the book ID from POST in subsequent GET, PUT, DELETE requests  
- **Dynamic Authentication**: Generate a unique email per run to avoid conflicts  
- **Assertions**:
  - HTTP status codes  
  - Response body content  
  - Response headers  
  - Validation of error messages

---

## Authentication Flow

1. A new user is created on each test execution  
2. A JWT access token is obtained via `/login`  
3. The token is applied automatically to all requests via `BaseTest`

---

## Reporting

- TestNG produces an HTML report in the `test-output` directory  
- A sample report screenshot is provided in the repo: `sample-report.png`

---

## Known Issues

- `createBook_MissingFields_ShouldReturn422` currently fails  
  - The API returns **500 Internal Server Error** instead of **422 Unprocessable Entity**, indicating missing input validation

---

## CI/CD Integration

- A GitHub Actions workflow is present at `.github/workflows/maven.yml`  
- Tests run automatically on every push to the repository

---

