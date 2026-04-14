# MedSync 🏥
**Medical Patient Management System**

MedSync is a professional-grade Spring Boot application designed to manage patient data with high reliability and strict business rules. 

## 🚀 Features
- **Full CRUD** for Patient Management.
- **Soft Delete** logic using Hibernate 6.
- **Data Mapping** with MapStruct (DTO Pattern).
- **Global Exception Handling** .
- **Professional Logging** with SLF4J.

## 🏗️ Architecture & Decisions
This project follows a layered architecture to ensure separation of concerns. All major architectural decisions are documented as **ADR (Architecture Decision Records)**.
Check them out in: `docs/adr/`

## 🛠️ Tech Stack
- **Java 21** (LTS)
- **Spring Boot 3.4+**
- **Spring Data JPA**
- **Lombok**
- **MapStruct**
- **H2 Database** (In-memory for development)

## 🚦 Getting Started
1. Clone the repository.
2. Run `./mvnw spring-boot:run`.
3. The API will be available at `http://localhost:8080`.
