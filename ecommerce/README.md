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
