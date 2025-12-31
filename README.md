Todo Application (Spring Boot)

  A simple Todo Application built using Spring Boot, Thymeleaf, and MySQL.
  This project helps users create, view, mark, and delete daily tasks through a clean web interface.

Features

  1. Add new tasks

  2. View all tasks

  3. Mark tasks as completed / undo

  4. Delete tasks

  5. Persistent storage using MySQL

  6. Clean UI using Thymeleaf + CSS

Tech Stack

  Backend: Spring Boot (Java)

  Frontend: Thymeleaf, HTML, CSS

  Database: MySQL

  ORM: Spring Data JPA (Hibernate)

  Build Tool: Maven


| Method | Endpoint       | Description            |
| ------ | -------------- | ---------------------- |
| GET    | `/`            | View all tasks         |
| POST   | `/`            | Create a new task      |
| GET    | `/{id}/toggle` | Toggle task completion |
| GET    | `/{id}/delete` | Delete a task          |



IDE: IntelliJ IDEA

Version Control: Git & GitHub
