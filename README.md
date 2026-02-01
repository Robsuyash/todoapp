Todo Application

A full-stack Todo application built using **Spring Boot** and **JavaScript**. This project demonstrates real-world backend concepts including authentication, authorization, security filters, and frontend–backend integration.

The application evolved from a basic CRUD system into a secure, JWT-authenticated platform with user-specific data isolation.

---

## Features

- **User Authentication:** Registration and login functionality.
- **Secure Storage:** Passwords hashed using BCrypt.
- **Stateless Auth:** JWT-based stateless authentication mechanism.
- **Data Isolation:** Users can only access and manage their own specific todos.
- **Task Management:** Create, view, toggle, and delete tasks.
- **Logout:** Secure logout support.

---

## Tech Stack

### Backend
- **Language:** Java 21
- **Framework:** Spring Boot 4.x
- **Security:** Spring Security, BCrypt, JWT (JJWT)
- **Database:** MySQL, Hibernate, Spring Data JPA

### Frontend
- **Core:** HTML, CSS, Vanilla JavaScript
- **Server:** Live Server (for local development)

---

##  Authentication Flow

1.  **Registration:** User registers; password is hashed and stored in MySQL.
2.  **Login:** User logs in; a **JWT token** is generated.
3.  **Storage:** The token is stored in the browser (localStorage/sessionStorage).
4.  **Request:** The token is sent via the Authorization header with every protected request.
5.  **Validation:** Backend validates the token, extracts identity, and allows access.

---

##  Security Highlights

* **Stateless Authentication:** No server-side session storage.
* **Password Hashing:** Industry-standard BCrypt implementation.
* **Custom Filters:** specific JWT filters to intercept and validate requests.

---

##  Project Structure

```text
src/main/java/com/yourpackage
├── controller      # REST Controllers
├── service         # Business Logic
├── repository      # Database Access (JPA)
├── model           # Entity Classes
├── security        # JWT & Security Config
└── dto             # Data Transfer Objects
<img width="1919" height="891" alt="Screenshot 2026-01-31 162647" src="https://github.com/user-attachments/assets/5f328e78-2094-445e-b9f4-9861d1380b7f" />
<img width="1919" height="898" alt="Screenshot 2026-01-31 162712" src="https://github.com/user-attachments/assets/aeb879aa-8c54-46b7-a29f-fd71377cbe4d" />
