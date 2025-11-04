# Microservices Architecture with API Gateway, Eureka, Auth, User, Product Services, and Common Module

This project implements a microservices architecture using Spring Boot and Spring Cloud. It consists of the following components:

- **API Gateway**: Routes and filters incoming requests.
- **Auth Service**: Handles user authentication and issues JWT tokens.
- **User Service**: Manages user data.
- **Product Service**: Manages products and catalogs.
- **Eureka Server**: Service discovery and registration.
- **Common Module**: A shared library containing reusable classes (DTOs, utilities, exceptions, constants).

---

## 🗺️ Architecture Overview

![Architecture Diagram](https://github.com/shoyebmd424/Distributed-Auth-System/blob/main/Authentication.gif)
---

## 📦 Common Module

- **Purpose**: Provides shared components and reduces code duplication.
- **Used by**: Auth, User, and Product services.
- **Contents**:
  - DTOs (Data Transfer Objects)
  - Custom Exceptions
  - Response Models (e.g., `ApiResponse`)
  - Enums & Constants
  - Utility Classes (e.g., JWT utility)

### Usage

Each service includes the common module as a dependency:
```xml
<dependency> 
  <groupId>com.commonModule</groupId>
	<artifactId>commonModule</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

# 🛡️ Microservice Authentication System

This project implements a secure microservice-based architecture using JWT for authentication, Spring Cloud Gateway for routing, and Eureka for service discovery.

---

## 🔐 Authentication Flow

1. **Login**  
   Clients send a `POST /api/auth/login` request with user credentials.

2. **Token Issuance**  
   The **Auth Service** validates the credentials and issues a **JWT token**.

3. **Authorization**  
   Clients include the token in the `Authorization: Bearer <token>` header for secure endpoints.

4. **Gateway Validation**  
   The **API Gateway** verifies the JWT token before forwarding the request to internal services.

---

## 🌐 API Gateway

- **Base URL Prefix**: `/api/**`
- **Responsibilities**:
  - Route external requests to internal services
  - Validate JWT tokens for secure endpoints

### 🔁 Routes

| Route Prefix        | Service         |
|---------------------|-----------------|
| `/api/auth/**`      | Auth Service    |
| `/api/users/**`     | User Service    |
| `/api/products/**`  | Product Service |

---

## 🧾 Auth Service

- **Endpoints**:
  - `POST /auth/login` – Authenticate user and return JWT token
  - `POST /auth/register` – Register a new user

- **Uses**:
  - Common DTOs and exception handling from `commonmodule`

---

## 👤 User Service

- **Endpoints**:
  - `GET /users/{id}` – Retrieve user by ID
  - `PUT /users/{id}` – Update user details

- **Uses**:
  - DTOs, `ApiResponse`, and shared exceptions from `commonmodule`

---

## 📦 Product Service

- **Endpoints**:
  - `GET /products` – List all products
  - `POST /products` – Add a new product

- **Uses**:
  - Shared models and exception handling from `commonmodule`

---

## 🔍 Eureka Server

- **URL**: `http://localhost:8761/`

### Configuration

```yaml
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
