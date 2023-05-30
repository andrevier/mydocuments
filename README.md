# Notebook App

Welcome to **My Documents**, a simple note-taking web application that allows users to create, read, update, and delete their documents with ease. This application is designed to showcase my skills in web development, database management, security implementation, and user management.

## Features

- **Create**: Users can create new documents and save them securely in the database.
- **Read**: Users can view their existing documents and access them whenever needed.
- **Update**: Users can edit and update their documents with the latest information.
- **Delete**: Users have the option to delete any unwanted documents from their collection.
- **Security**: The app is built with robust security measures using Spring Security to ensure the confidentiality and integrity of user data.
- **User Management**: Each user has their own set of documents, which are displayed on the home page for easy access.

## Technologies Used

The following technologies and frameworks are used in the development of this application:

- **Spring Boot**: A powerful framework for building Java-based web applications.
- **Spring Data JPA**: A module that provides enhanced support for interacting with databases using the Java Persistence API (JPA).
- **PostgreSQL**: A reliable and scalable relational database management system (DBMS) used to store and manage the application's data.
- **Thymeleaf**: A modern server-side Java template engine for building dynamic web pages.
- **Spring Security**: A highly customizable framework for implementing authentication and authorization in Java applications.

## Entity-Relationship Model

The application revolves around three main entities: user, privilege, and document. These entities are organized within the `entities` folder. The relationship rules between these entities are as follows:

1. A user can have zero or more privileges.
2. Each privilege belongs to only one user.
3. A user can have zero or many documents.

Based on these rules, the user and privilege have a one-to-many relationship, as well as the user and documents.

## To-Do List

To showcase the functionality and features of this application, the following tasks are planned:

1. **Register Page**: Implement a registration page where new users can sign up for an account.
2. **Create and Delete Documents**: Enable users to create new documents and delete unwanted ones.
3. **Test Security**: Thoroughly test the security implementation to ensure the protection of user data.
4. **User Access Management**: Implement a feature where users can grant access to their documents to other users.

Feel free to explore this repository. If you have any questions or feedback, please don't hesitate to reach out.