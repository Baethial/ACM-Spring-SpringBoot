# Sistema de Gestión Comercial API

## Project Overview

This is a comprehensive backend application built with **Spring Boot** and **Spring Data JPA** designed for managing core commercial operations, including users, products, stores, categories, and sales. It provides a robust RESTful API layer for all CRUD (Create, Read, Update, Delete) operations, along with extensive querying and data aggregation capabilities.

### Key Features

* **Layered Architecture:** Clear separation of concerns (Controller, Service, Repository).
* **JPA/Hibernate:** Object-Relational Mapping for seamless database interaction with PostgreSQL.
* **Complete RESTful Endpoints:** Fully documented API for 10 domain entities.
* **Complex Queries:** Advanced filtering, ordering, and aggregation logic implemented using **JPQL** and **Spring Data JPA** method conventions.
* **Explicit Bridge Entities:** Correct handling of many-to-many relationships with attributes (`ProductsStoresEntity` and `SalesProductsEntity`).

-----

## Technology Stack

| Component | Technology | Version / Annotation | Purpose |
| :--- | :--- | :--- | :--- |
| **Backend Framework** | Spring Boot | 3.x | Application Core |
| **Database** | PostgreSQL | N/A | Persistent Data Store |
| **ORM** | Spring Data JPA / Hibernate | `@Entity`, `@Repository` | Data Persistence Layer |
| **Build Tool** | Maven | N/A | Dependency Management |
| **Language** | Java | 17+ | Programming Language |
| **Utility** | Lombok | `@Getter`, `@Setter` | Boilerplate Code Reduction |

-----

## Getting Started

### Prerequisites

1.  **Java Development Kit (JDK) 17 or higher.**
2.  **Maven 3.x or higher.**
3.  **PostgreSQL Database:** A running instance with connection details matching `application.properties`.

### Database Configuration

The application connects to a PostgreSQL database. Verify the following properties in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestion_comercial_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

The `spring.jpa.hibernate.ddl-auto=update` setting will automatically manage table creation based on the JPA entities.

### Running the Application

1.  **Clone the repository.**
2.  **Build the project:** `mvn clean install`
3.  **Run the Spring Boot application:** `mvn spring-boot:run`

The application will start on the default port **8080**.

-----

## Core Entity Mappings & Relationships

The system's data model includes **10 JPA entities**. Note the use of **Explicit Bridge Entities** for relationships requiring extra data (e.g., `stock` or `quantity`).

| Entity | Mapped Table | Primary Relationships | Notes |
| :--- | :--- | :--- | :--- |
| **DepartmentEntity** | `departamentos` | OneToMany (City) | Top-level geographic entity. |
| **CityEntity** | `ciudades` | ManyToOne (Department) | Geographic entity for location. |
| **RoleEntity** | `roles` | OneToMany (User) | Defines user permissions. |
| **UserEntity** | `usuarios` | ManyToOne (City, Role) | Stores customer/employee data. |
| **CategoryEntity**| `categorias` | ManyToMany (Product) | Groups products. |
| **StoreEntity** | `almacenes` | ManyToOne (City) | Physical store locations. |
| **ProductEntity** | `productos` | ManyToMany (Category), OneToMany (ProductsStores) | Core inventory item. |
| **SaleEntity** | `ventas` | ManyToOne (User), OneToMany (SalesProducts) | Records a transaction header. |
| **ProductsStoresEntity** | `productos_almacenes` | ManyToOne (Product, Store) | **Explicit Bridge:** Stores product **stock** per store. |
| **SalesProductsEntity** | `ventas_productos` | ManyToOne (Sale, Product) | **Explicit Bridge:** Stores **quantity** and **price\_at\_sell** for line items. |

-----

## Complete API Endpoints Reference

The following tables document **every** API endpoint defined in the Controller layer, including all standard CRUD and custom advanced queries.

### 1\. Geographical Endpoints (`/departments`, `/cities`)

| Entity | Method | Path | Description | Query Type |
| :--- | :--- | :--- | :--- | :--- |
| **Department** | `POST/GET/PUT/DELETE` | `/departments`, `/departments/{id}` | Standard CRUD operations. | CRUD |
| **City** | `POST/GET/PUT/DELETE` | `/cities`, `/cities/{id}` | Standard CRUD operations. Requires `Department` foreign key. | CRUD |

### 2\. User & Role Endpoints (`/roles`, `/users`)

| Entity | Method | Path | Description | Query Implementation |
| :--- | :--- | :--- | :--- | :--- |
| **Role** | `POST/GET/PUT/DELETE` | `/roles`, `/roles/{id}` | Standard CRUD operations. | CRUD |
| **User** | `POST/GET/PUT/DELETE` | `/users`, `/users/{id}` | Standard CRUD operations. Requires `Role` and `City` foreign keys. | CRUD |
| **User** | `GET` | `/users/last_name/` | Get user by exact last name match. (`@RequestParam lastName`) | **Advanced:** `findByLastName()` |
| **User** | `GET` | `/users/city/` | List all users in a specific city name. (`@RequestParam city`) | **Advanced:** `findAllByCity_Name()` |
| **User** | `GET` | `/users/department/` | List all users in a specific department name. (`@RequestParam department`) | **Advanced:** `findAllByCity_Department_Name()` |
| **User** | `GET` | `/users/name/` | Search users by a partial first name fragment. (`@RequestParam nameFragment`) | **Advanced:** `findAllByFirstNameContaining()` |

### 3\. Inventory & Category Endpoints (`/categories`, `/products/`)

| Entity | Method | Path | Description | Query Implementation |
| :--- | :--- | :--- | :--- | :--- |
| **Category** | `POST/GET/PUT/DELETE` | `/categories`, `/categories/{id}` | Standard CRUD operations. | CRUD |
| **Product** | `POST/GET/PUT/DELETE` | `/products/`, `/products/{id}` | Standard CRUD operations. | CRUD |
| **Product** | `GET` | `/products/category/` | List products belonging to a specific category name. (`@RequestParam categoryName`) | **Advanced:** `findAllByCategories_Name()` |
| **Product** | `GET` | `/products/price/` | List products within a specified price range. (`@RequestParam minPrice`, `maxPrice`) | **Advanced:** `findAllByPriceBetween()` |
| **Product** | `GET` | `/products/price_order_desc/` | List all products sorted by price, highest first. | **Advanced:** `findAllByOrderByPriceDesc()` |
| **Product** | `GET` | `/products/price_order_asc/` | List all products sorted by price, lowest first. | **Advanced:** `findAllByOrderByPriceAsc()` |
| **Product** | `GET` | `/products/creation_date/`| List products created after a specific timestamp. (`@RequestParam date`) | **Advanced:** `findAllByCreationDateAfter()` |
| **Product** | `GET` | `/products/store/` | List all products stocked in a specific store name. (`@RequestParam storeName`) | **Advanced:** Query on `ProductsStoresEntity` and mapping in Service. |
| **Product** | `GET` | `/products/best_sellers/` | Top 3 products based on total units sold. | **Aggregation (JPQL):** Custom `@Query` on `SalesProductsEntity`. |

### 4\. Store Endpoints (`/stores`)

| Entity | Method | Path | Description | Query Type |
| :--- | :--- | :--- | :--- | :--- |
| **Store** | `POST/GET/PUT/DELETE` | `/stores`, `/stores/{id}` | Standard CRUD operations. Requires `City` foreign key. | CRUD |
| **Store** | `GET` | `/stores/city/` | List all stores located in a specific city name. (`@RequestParam city`) | **Advanced:** `findAllByCity_Name()` |

### 5\. Sales Endpoints (`/sales`)

| Entity | Method | Path | Description | Query Implementation |
| :--- | :--- | :--- | :--- | :--- |
| **Sale** | `POST/GET/PUT/DELETE` | `/sales`, `/sales/{id}` | Standard CRUD operations. Requires a valid `User` ID. | CRUD |
| **Sale** | `GET` | `/sales/user/` | List all sales made by a specific User ID. (`@RequestParam userId`) | **Advanced:** `findAllByUserEntity_Id()` |
| **Sale** | `GET` | `/sales/date/` | Calculates the **total sales amount** for a specific date. (`@RequestParam date`) | **Aggregation (JPQL):** `SELECT SUM(total) FROM ...` |
| **Sale** | `GET` | `/sales/greater/` | List sales with a total amount exceeding a threshold. (`@RequestParam amount`) | **Advanced:** `findAllByTotalGreaterThan()` |

-----

## Architecture Deep Dive

### Query Implementation Pattern

All application logic adheres to the standard layered architecture:

1.  **Controller:** Handles HTTP requests, parameter parsing, and response formatting (Status Codes).
2.  **Service:** Contains all **business logic**, data validation, transaction management (`@Transactional`), and coordinates complex operations.
3.  **Repository:** Executes database queries, leveraging Spring Data JPA's method naming conventions or custom **JPQL** via `@Query`.
4.  **Entity:** Defines the persistence model and relationships.

### Explicit Bridge Entities for Many-to-Many

The project uses two crucial explicit bridge entities for relationships that require carrying extra data:

1.  **`ProductsStoresEntity`**: Manages the relationship between a **Product** and a **Store**, storing the **stock** for that pairing.
2.  **`SalesProductsEntity`**: Manages the relationship between a **Sale** and a **Product**, storing the transactional details: the **quantity** sold and the **price\_at\_sell**.

**Querying Strategy:** Queries that depend on these bridge attributes (e.g., "Products by Store" or "Best Sellers") must target the bridge repository first, with the Service layer responsible for mapping the bridge results back to the core entities (Product or Sale).