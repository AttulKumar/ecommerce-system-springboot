# Spring Boot E-Commerce Web Application

A full-stack e-commerce web application built using **Spring Boot, MySQL, HTML, CSS, and JavaScript**.

This project includes separate flows for **Admin** and **Customer** users. Admin can manage products, while customers can browse products, add items to cart, place orders, and view invoices.

---

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

### Frontend
- HTML
- CSS
- JavaScript
- Fetch API

---

## Features

### User Features
- User registration
- User login
- Role-based redirect
  - Admin dashboard
  - Customer dashboard

### Admin Features
- Add product
- View all products
- Search products
- Update product
- Delete product

### Customer Features
- View products
- Search products
- Add products to cart
- Select quantity before adding to cart
- View cart
- Remove item from cart
- Checkout/place order
- Stock reduction after checkout
- View order history
- View invoice

---

## Project Structure

```text
ecommerce
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.ecommerce.ecommerce
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── model
│   │   │       ├── repository
│   │   │       └── service
│   │   │
│   │   └── resources
│   │       ├── static
│   │       │   ├── index.html
│   │       │   ├── login.html
│   │       │   ├── register.html
│   │       │   ├── admin.html
│   │       │   ├── customer.html
│   │       │   └── style.css
│   │       │
│   │       └── application.properties
│   │
│   └── test
│
├── pom.xml
└── README.md


---

## Backend APIs

### Auth APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register new user |
| POST | `/auth/login` | Login user |

### Product APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/products` | Get all products |
| POST | `/products` | Add new product |
| PUT | `/products/{id}` | Update product |
| DELETE | `/products/{id}` | Delete product |
| GET | `/products/search?keyword=name` | Search product by name |

### Cart APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/cart/add` | Add product to cart |
| GET | `/cart/{userId}` | View cart by user |
| DELETE | `/cart/item/{id}` | Remove cart item |

### Order APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/orders/place/{userId}` | Place order |
| GET | `/orders/{userId}` | View order history |
| GET | `/orders/invoice/{orderId}` | View invoice |



_____________


Database Configuration

Create a MySQL database:

CREATE DATABASE ecommerce_db;

Update src/main/resources/application.properties:

spring.application.name=ecommerce

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

Hibernate will automatically create the required tables.

______________


Frontend Pages
| Page               | URL                                   |
| ------------------ | ------------------------------------- |
| Home               | `http://localhost:8080/`              |
| Register           | `http://localhost:8080/register.html` |
| Login              | `http://localhost:8080/login.html`    |
| Admin Dashboard    | `http://localhost:8080/admin.html`    |
| Customer Dashboard | `http://localhost:8080/customer.html` |

___________________


Sample Admin Flow
Register as admin.
Login with admin account.
Add products.
View, update, search, or delete products.


Sample Customer Flow
Register as customer.
Login with customer account.
View products.
Add product to cart with quantity.
View cart.
Checkout.
View order history.
View invoice.

___________________

Future Improvements
Add Spring Security and JWT authentication
Encrypt passwords using BCrypt
Add product images
Improve frontend design
Add payment gateway simulation
Add admin order management
Deploy backend and database online

______________________



Author

Atul Thakur
