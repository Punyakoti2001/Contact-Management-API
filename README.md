# Contacts Management Application

This is a full-stack application for managing contacts, supporting CRUD operations, contact merging, and user authentication. The backend is built with Spring Boot, and the frontend is developed in React.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Design Choices](#design-choices)
- [Backend Setup (Spring Boot)](#backend-setup-spring-boot)
- [Dependecies (Spring Boot)](#spring-boot-stater)
- [Frontend Setup (React)](#frontend-setup-react)
- [Dependecies (React Js)](#ReactJs)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)

---

## Technologies Used

- **Frontend**: React, Axios, Bootstrap 
- **Backend**: Spring Boot, Spring Data JPA, Hibernate, Spring Security, Spring Cache
- **Database**: H2 (in-memory) or MySQL
- **Other Tools**: Swagger for API documentation, Jackson for JSON serialization

## Design Choices

- **Modular Structure**: The backend is structured into layers (Controller, Service, Repository) to separate concerns.
- **Caching**: Implemented caching on frequently accessed data to improve performance.
- **Validation**: Both frontend and backend validations are used for better data integrity.
- **Asynchronous Operations**: Some backend services use `@Async` for better performance, especially in CRUD operations.
- **Swagger**: Used for auto-generating API documentation for easier API testing and integration.
- **Spting security**: Spring Security for Role-Based Access Control (RBAC) with JWT

---

## Backend Setup (Spring Boot)

### Prerequisites

- Java 17 or higher
- Maven
- (Optional) Postgres if not using H2

### Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/contacts-management.git
   cd contacts-management/backend



## Dependencies:
- Spring Data JPA
- Spring Validation
- Spring Web 
- DevTools
- Spring Boot Actuator
- Spring Boot Starter Security
- PostgreSQL
- H2 Database
- Lombok
- SpringDoc OpenAPI
- MapStruct
- JSON Web Token (JWT)

## Frontend Setup(React)

**React Contact Management App** 

This is a simple React application for managing contacts. It allows users to add, edit, display, and delete contacts. 
## Features 
- Add new contacts 
- Edit existing contacts 
- Display all contacts 
- Delete contacts

## Dependencies:
- React
- react-router-dom
- axios
- react-toastify



## Running the Application
**Run Backend**: Follow the backend setup instructions to start the Spring Boot server.

1. Clone the Repository:
    
        https://github.com/Punyakoti2001/Contact-Management-API.git

2. Build the Project: 
  Ensure you have Maven installed, then build the project:

         mvn clean install

3. Run the Application:

         mvn spring-boot:run

**The application will start on http://localhost:9091 (default port).**



**Run Frontend**: Follow the frontend setup instructions to start the React server.

These instructions will help you set up the project on your local machine. 
### Prerequisites
 Make sure you have Node.js and npm (Node Package Manager) installed on your machine.

   - [Node.js](https://nodejs.org/) 
   - [npm](https://www.npmjs.com/) 
 ## Installation 
 1. Clone the repository to your local machine:

 git clone 

           https://github.com/Punyakoti2001/Contact-Management-API.git

2. Navigate to the project directory:
         
       cd react-contact-app

3. Install the dependencies:

        npm install

4. Running the App

       npm start

5. Building the App

       npm run build


**Access the Full Application**: Visit http://localhost:3000 to access the frontend, which interacts with the backend on port 9091.

## API Documentation

**Overview**
The backend API supports the following key 

**operations:**

**Create a Contact:** POST /build-bot/v1/contact/create-contact
**Update a Contact:** PUT /build-bot/v1/contact/update-contact/{contactId}

**Fetch Contacts:**
- Fetch all contacts: GET /build-bot/v1/contact/fetch-all-contacts
- Fetch by phone number: GET /build-bot/v1/contact/fetchByField?phoneNo={phoneNo}
- Fetch by email: GET /build-bot/v1/contact/fetchByField?email={email}
**Delete a Contact:** DELETE /build-bot/v1/contact/delete-contact?contactId={contactId}

**Merge Contacts:** POST /build-bot/v1/contact/merge-contacts


## Swagger Documentation
You can view all endpoints and try them out using Swagger:

URL: http://localhost:9091/swagger-ui.html


## Project Structure
**Backend Structure (Spring Boot)**

src/

├── main/

│   ├── java/

│   │   ├── com.buildbot.contactsmanagement/

│   │   │   ├── controller/         # REST 
controllers

│   │   │   ├── entity/             # Entity classes

│   │   │   ├── exceptionhandling/  # Custom exceptions and handlers

│   │   │   ├── repository/         # Repository interfaces

│   │   │   ├── requestDto/         # Data transfer objects for requests

│   │   │   ├── responseDto/        # Data transfer objects for responses

│   │   │   ├── security/           # JWT and security configurations

│   │   │   ├── service/            # Service interfaces

│   │   │   ├── serviceimpl/        # Service implementation classes

│   │   │   └── ContactManagementApiApplication.java  # Main application file

│   └── resources/

│       ├── application.properties  # Application configuration

│       ├── data.sql                # Optional initial data

│       └── schema.sql              # Optional schema definition

└── test/                           # Unit and integration tests


**Frontend Structure (ReactJS)**

contacts-management-api-ui/

├── public/

│   ├── index.html

│   └── ...

├── src/

│   ├── components/

│   │   ├── contact/

│   │   │   ├── AddContact.js

│   │   │   ├── DisplayContacts.js

│   │   │   ├── Home.js

│   │   ├── services/

│   │   │   ├── ContactService.js

│   │   └── ...

│   ├── App.css

│   ├── App.js

│   ├── index.js

│   └── ...

├── .gitignore

├── package.json

└── README.md




## Additional Notes
**Database:** Default is H2 for development. Switch to MySQL in application.properties for production.

**Error Handling:** Custom exceptions are used in the backend to return user-friendly error messages.

**Asynchronous Processing:** @Async annotation is applied to non-blocking operations to improve response times.

## Your Name - 
 **pulicherlapunyakotireddy@gmail.com**

## Project Repository -
**https://github.com/Punyakoti2001/Contact-Management-API.git**

# Backend Application file Name: 
 **contacts-management-api**
# Frontend Application file Name:
**contacts-management-api-ui**
