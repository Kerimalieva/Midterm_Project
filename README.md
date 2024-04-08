# Midterm Project

This project is a web application developed using Spring Boot framework, providing functionalities for managing products, user registration, authentication, and authorization. Below is an overview of the project structure and functionalities:

## Project Structure

### `configurations` Package
- **MvcConfig:** Configures resource handling for static files.
- **SecurityConfig:** Configures security settings such as URL authorization, authentication manager, password encoding, etc.

### `controllers` Package
- **AdminController:** Controller for admin-related functionalities such as listing users, banning users, and editing user roles.
- **ImageController:** Controller for serving images stored in the database.
- **ProductController:** Controller for managing products including creation, deletion, and listing.
- **UserController:** Controller for user-related functionalities like user registration, login, and profile management.

### `models` Package
- **User:** Entity class representing user details.
- **Product:** Entity class representing product details.
- **Image:** Entity class representing image details associated with products.
- **Role:** Enum class representing user roles.

### `repositories` Package
- **UserRepository:** Repository interface for user data access.
- **ProductRepository:** Repository interface for product data access.
- **ImageRepository:** Repository interface for image data access.

### `services` Package
- **CustomUserDetailsService:** Service for loading user details during authentication.
- **UserService:** Service for user-related operations like user creation, role management, etc.
- **ProductService:** Service for product-related operations like product creation, deletion, etc.

## Functionalities

- **User Registration and Authentication:** Users can register for an account and log in securely.
- **Role-based Authorization:** Admins have access to admin functionalities like user management, while regular users have access to product-related functionalities.
- **Product Management:** Users can create, delete, and view their products.
- **User Management:** Admins can view users, ban/unban users, and edit user roles.

## Setup Instructions

1. **Clone the Repository:** Clone this repository to your local machine.
2. **Database Configuration:** Configure your database settings in `application.properties`.
3. **Build and Run:** Build the project using Maven and run it using Spring Boot.

```
mvn clean install
mvn spring-boot:run
```

4. **Access the Application:** Access the application through the provided URL, I used `http://localhost:8091`.

## Technologies Used

- Spring Boot
- Spring Security
- Spring Data JPA
- Thymeleaf
- Lombok


Zarina Kerimalieva COM-22A



