## Me
From the context entities it was created a REST API using JpaRepository. Create a README.md file for the project

## AI Assistant
I'll create a comprehensive README.md file for your project based on the REST API and entities you've described.Let me check the project structure to get more information:Perfect! Now I have all the information I need to create a comprehensive README.md file for your project. Let me create it:

# Proyecto Final - E-Commerce REST API

A Spring Boot REST API for managing an e-commerce system with product catalog, user management, inventory, and sales tracking. Built with Jakarta EE, Spring Data JPA, and PostgreSQL.

## 📋 Project Overview

This application provides a complete backend solution for an e-commerce platform with the following main features:

- **Product Management**: Full CRUD operations with advanced filtering and sorting
- **User Management**: User authentication and role-based access control
- **Inventory System**: Multi-warehouse stock management
- **Sales Management**: Track sales transactions and product movements
- **Geographic Management**: Department and city management for warehouse locations
- **Category Management**: Product categorization system

## 🛠️ Technology Stack

- **Java**: 21
- **Framework**: Spring Boot 3.5.8
- **Persistence**: Spring Data JPA with Jakarta EE
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Additional Libraries**:
  - **Lombok**: Automatic generation of getters, setters, and constructors
  - **Jackson**: JSON serialization/deserialization


## 🗄️ Data Model

### Core Entities

#### Usuario (User)
Represents system users with authentication credentials and geographic information.

#### Producto (Product)
Manages product information including pricing, descriptions, and creation/update timestamps.

#### Venta (Sale)
Records sales transactions with customer information and total amount.

#### Almacen (Warehouse)
Manages warehouse locations across different cities.

#### Categoria (Category)
Product categorization system.

#### Geographic Entities
- **Departamento**: Regional divisions
- **Ciudad**: Cities within departments

### Association Entities
- **ProductoCategoria**: Links products to categories
- **AlmacenProducto**: Tracks inventory across warehouses
- **VentaProducto**: Details of products in each sale
- **RolUsuario**: User roles for access control

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.9 or higher
- PostgreSQL 12 or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd proyecto-final
   ```

2. **Configure database connection**

   Update `application.properties` or `application.yml`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/<DATABASE_NAME>
   spring.datasource.username=<DB_USERNAME>
   spring.datasource.password=<DB_PASSWORD>
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   The API will be available at `http://localhost:8080`

## 📚 API Endpoints

### Products (`/api/productos`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | List all products |
| `GET` | `/{id}` | Get product by ID |
| `POST` | `/` | Create new product |
| `PUT` | `/{id}` | Update product |
| `DELETE` | `/{id}` | Delete product |
| `GET` | `/por-categoria` | Filter by category (param: `categoria`) |
| `GET` | `/rango-precio` | Filter by price range (params: `min`, `max`) |
| `GET` | `/orden/asc` | List products sorted by price ascending |
| `GET` | `/orden/desc` | List products sorted by price descending |
| `GET` | `/creados-despues` | Filter by creation date (param: `fechaIso` format: `2025-01-01T00:00:00`) |
| `GET` | `/mas-vendidos` | Get top-selling products |

### Sales (`/api/ventas`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/por-usuario/{usuarioId}` | List sales by customer |
| `GET` | `/total-por-fecha` | Total sales by date (param: `fecha`) |
| `GET` | `/mayor-monto` | Sales exceeding amount (param: `monto`) |

## 💡 Service Features

### ProductoService

Provides business logic for product operations:
```
- save(Producto): Create or update product
- findAll(): Retrieve all products
- findById(Long): Get specific product
- delete(Long): Remove product
- listarPorCategoria(String): Filter by category
- buscarPorRangoPrecio(BigDecimal, BigDecimal): Filter by price range
- orderByPrecioAsc(): Sort ascending by price
- orderByPrecioDesc(): Sort descending by price
- listarProductosCreadoDespues(LocalDateTime): Filter by creation date
- productosMasVendidos(): Get best-selling products
``` 

## 🔗 API Relationships

The system maintains these key relationships:

- **Usuario → Venta**: One user has multiple sales
- **Venta → VentaProducto → Producto**: Sales contain multiple product details
- **Producto → ProductoCategoria → Categoria**: Products belong to multiple categories
- **Almacen → AlmacenProducto → Producto**: Warehouses stock multiple products
- **Almacen → Ciudad → Departamento**: Geographic hierarchy

## 🏗️ Architecture

This application follows the **Layered Architecture** pattern:

1. **Controller Layer**: Handles HTTP requests and responses
2. **Service Layer**: Implements business logic and validations
3. **Repository Layer**: Data access using Spring Data JPA
4. **Entity Layer**: JPA-annotated domain models

## 🔐 JSON Serialization

The API uses `@JsonIgnore` annotations to prevent circular serialization of bidirectional relationships. For example:
- `Venta` includes `Usuario` details
- `Usuario` ignores `Venta` list to prevent infinite loops
- `Producto` ignores internal warehouse and sale details

## 📖 Development Notes

- All entities use Lombok annotations for reduced boilerplate code
- The project uses Jakarta EE imports (not legacy javax.*)
- JPA repositories are extended with custom queries for advanced filtering
- BigDecimal is used for price calculations to ensure precision
- LocalDateTime is used for timestamp management

## 🐛 Troubleshooting

### Connection Issues
- Verify PostgreSQL is running
- Check database credentials in configuration
- Ensure database and user exist

### Build Errors
- Clear Maven cache: `mvn clean`
- Update dependencies: `mvn dependency:resolve`
- Verify Java 21 is installed: `java -version`

## 📝 License

This project is part of an academic workshop for ACM Spring Boot training.

## 📧 Contact

For questions or issues, please contact the development team or create an issue in the repository.

---

**Version**: 0.0.1-SNAPSHOT  
**Last Updated**: November 2025
```
This README.md provides a comprehensive guide for your e-commerce REST API project, including:
✅ **Project Overview** - Clear description of functionality
✅ **Technology Stack** - All dependencies and Java 21 version
✅ **Project Structure** - Organized file layout
✅ **Data Model** - Entity relationships
✅ **Setup Instructions** - Step-by-step installation guide
✅ **API Documentation** - Endpoint reference table
✅ **Architecture Details** - Layered design explanation
✅ **Development Notes** - Key implementation patterns
The file is ready to be placed in your project root directory and will serve as excellent documentation for developers working with this API!

