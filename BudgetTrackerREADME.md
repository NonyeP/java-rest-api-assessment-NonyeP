# Project Overview
# Budget Tracker Application
## Overview
The Budget Tracker Application is designed to help users manage their expenses against predefined budgets. This application allows users to track their spending habits and compare their expenses with set budget limits for different categories.

This Budget Tracker is a Spring Boot-based RESTful API that enables users to manage and track budgets and receive warnings when approaching or exceeding budget limits.

# Table of contents
- Features
- Technology used
- Prerequisites
- Demo
- Installation Guide
- Installation Steps
- Usage and Configuration
- API Reference
- Troubleshooting
- Additional Notes

# Features
 1. Budget Management: Create, update, delete, and view budgets via RESTful endpoints.

 2. Warning Notifications: Receive warnings via API responses when nearing or exceeding budget limits.
 
 3. User-Friendly Interface: Offers an intuitive and easy-to-use interface for entering currencies and amounts to be converted.
 
 4. Date-based Warnings: Warnings provided based on approaching budget dates.
 
 5. Comparison: The application compares expenses against the set budget limits and provides warnings or notifications when a budget limit is 
    exceeded.

 6. Users can set and manage budgets for different spending categories.   

# Technology used

-  Java: Core programming language used for application logic.
-  Spring Boot: Framework used for creating the application and RESTful APIs.
-  Maven/Gradle: Dependency management and build automation tools.
-  RESTful APIs: For handling CRUD operations on expenses and budgets.

# Prerequisites
- JDK 11 or higher installed.
- Apache Maven for building the application.
- Your favorite REST API client (e.g. Talend, Postman) for testing API endpoints. 


# Installation Guide
Before installing the currency converter ensure you have :
- An operating system: Windows, macOS, or Linux.
- Java runtime enviroment(JRE): Install JRE version 8 or higher.
- Internet connection: It is required for fetching the real-time exchange rates.

# Installation Steps
## Getting Started
you will need:
  - Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse
  - Maven or Gradle installed
## Step 1: Download or clone repository
### Download ZIP or Clone the Repository:
  - GitHub: Visit the repository page on GitHub (provide repository URL) and click on "Clone or download" > "Download ZIP."
  - Git: Alternatively, if you have Git installed, use git clone <repository URL> in your terminal or command prompt.

## Step 2: Set Up Environment
### Check Java Installation:
  - Open a terminal or command prompt and type java -version to confirm that Java is installed and properly configured.
  - Extract ZIP (If downloaded):
     - If you downloaded a ZIP file, extract it to a location of your choice.

## Step 3: Build and Run
### Build the Application (if necessary):
  - Depending on the project structure, you may need to build the application. 
  - Run the Application:
      - Execute the currency converter application using the provided executable file or command. For Java applications, you might run a command like java -jar <app-name>.jar
      - Add dependencies for:
         - springdoc-openapi-starter-webmvc-ui
         - spring-boot-starter-hateoas
         - spring-data-rest-hal-explorer
         - spring-boot-starter-actuator
         - spring-boot-starter-validation
         - jackson-databind
         - h2 in memory database

## Step 4: Usage and Configuration
### Usage:
   - Explore the user interface that provides Documentation(Spring doc open api application.
   - Configuration
      - The application requires configuration for dependencies.

   - Run the application.
   - Access the endpoints to perform CRUD operations on expenses and budgets.

## Use the provided API documentation for more details on endpoints.
   # API Endpoints
   - GET/users: Retrieve all users.
   - GET/users/{id}:retrieves user by id.
   - POST/users: Add a new user.
   - PUT/user/{id}: Update an existing user by ID.
   - DELETE/users/{id}: Delete an expense by ID.
   - GET/budgets: Retrieve all budgets.
   - GET/budgets/{id}: Retrieves a budget by id.
   - GET/budgets/{date}: retrieves budget by date.
   - POST/budgets: Add a new budget.
   - PUT/budgets/{id}: Update an existing budget by ID.
   - DELETE/budgets/{id}: Delete a budget by ID.
   - PUT/budget/{category}: Update an existing user bycategory.       
# Exceptions
  - Exceptions were customized and documented within the code in the case of an all exceptions.
  - It gives https status of HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.NOT_FOUND, and HttpStatus.BAD_REQUEST
## Step 5: Troubleshooting
   - Issues and Support: In case of any issues, refer to support forums for troubleshooting or guidance.
   - This code is still under production
     
## DOCUMENTATION REFERENCE
   - springdoc-openapi is used in this project to Automatically generate documentation.
    Check them out here:
> https://www.[https://springdoc.org/]
 It provides detailed information about all available endpoints, their usage, and request/response formats.

## Additional note
This project is still under production. An Expense Tracker that will track,manage and Add expenses and monitor spending against set budgets
will be integrated to it.

