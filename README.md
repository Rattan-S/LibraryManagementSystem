# 📚 Library Management System

A full-stack Library Management System built using Java Spring Boot. The application allows users to browse books, view details, write reviews, and provides secure role-based access for administrators and regular users.

---

## Features

### Public Users
- View all books
- View detailed book information
- Read book reviews

### Registered Users
- Login securely
- Add reviews to books

### Administrators
- Add new books
- Manage library content

---

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Security
- JDBC
- HTML5
- CSS3
- Thymeleaf
- H2 Database
- Maven

---

## Security Features

- BCrypt password encryption
- Spring Security authentication
- Role-based authorization
    - ADMIN
    - USER
- Protected admin routes
- Custom login page
- Access denied page

---

## Project Structure

```
src
├── beans
├── controllers
├── database
├── security
├── services
├── templates
└── static
```

---

## Main Functionality

- User registration
- User login
- View available books
- View book details
- Add reviews
- Admin can add books
- Role-based access control

---

## Database

The application uses an H2 relational database.

Tables include:

- users
- roles
- user_roles
- books
- reviews

---

## How to Run

1. Clone the repository

```bash
git clone https://github.com/yourusername/LibraryManagementSystem.git
```

2. Open the project in Eclipse or IntelliJ.

3. Run the Spring Boot application.

4. Visit:

```
http://localhost:8080
```

---

## Skills Demonstrated

- Spring Boot development
- MVC architecture
- CRUD operations
- Authentication and authorization
- Password encryption
- Database design
- Java object-oriented programming
- Role-based security
- RESTful routing
- Git version control

---

## Future Improvements

- Edit and delete books
- Edit reviews
- Search books
- Book categories
- Pagination
- Book cover image upload
- Admin dashboard
- User profile page

---

## Author

Rattan Singh
