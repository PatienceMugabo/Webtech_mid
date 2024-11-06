# 📖 Patience's Blog Application

![Spring Boot](https://img.shields.io/badge/SpringBoot-2.5.4-brightgreen)
![Java](https://img.shields.io/badge/Java-21-orange)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0.12-blue)
![H2](https://img.shields.io/badge/Database-H2-informational)

Welcome to the *Spring Boot Blog Application* — a full-featured, secure blog platform built with Java Spring Boot and Thymeleaf. This project provides a seamless and user-friendly experience for creating, reading, updating, and deleting blog posts in a clean, modern web interface.

## 🌐 Key Features

- *CRUD Operations*: Users can create, read, update, and delete blog posts.
- *MVC Architecture*: Designed using Model View Controller (MVC) for structured and maintainable code.
- *Secure Access*: Integration of Spring Security for user authentication and authorization.
- *In-Memory Database*: Uses H2 database for easy setup and testing.
- *Responsive UI*: Built with Thymeleaf for dynamic content rendering.

## 🎥 Video Walkthrough

Take a quick tour of the application to see how it works:
[![Loom Video](https://img.shields.io/badge/Watch-Video-blue)](https://www.loom.com/share/1466c59b434b45fe8c6a52703b54a5be?sid=634e0b35-f46b-4b84-92ee-2af5f345acf5)



## 🚀 Getting Started

1. *Clone the repository*:
   bash
   git clone https://github.com/PatienceMugabo/Webtech_mid.git
   cd spring-boot-blog
   

2. *Run the application*:
   bash
   ./mvnw spring-boot:run
   

3. *Access the application*:
   Open your browser and navigate to http://localhost:8080.

## ⚙️ Technologies Used

- *Java Spring Boot*: For building and running the application.
- *Thymeleaf*: Templating engine for rendering views.
- *Spring Data JPA*: Manages data with JPA repositories.
- *H2 Database*: Lightweight, in-memory database for quick testing.
- *Spring Security*: Ensures secure access to the application.

## 🛠 Configuration

The application runs on an H2 database by default. You can configure it by editing src/main/resources/application.yml.

## 🔒 Authentication

This application uses *Spring Security* for authentication. Only registered users can create or edit posts, while anyone can view them. Passwords are stored securely using bcrypt hashing.
