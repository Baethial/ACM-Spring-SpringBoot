# 🛒 Punto 2 — Los clones del contenedor

## *Tema: Scopes y gestión de instancias* 

Este punto tiene como objetivo comprender cómo funcionan los **scopes de los beans** (`singleton` y `prototype`) dentro del contenedor de Spring, aplicándolo a un caso de un **carrito de compras**.

---

## 🧩 Descripción del reto

### 🧠 Escenario:
Los usuarios reportan que **todos los carritos comparten los mismos productos**.  
Esto sucede porque el bean `ShoppingCart` fue definido como `singleton`, lo que hace que todos los usuarios compartan **la misma instancia del carrito**.

---

## 🎯 Objetivos y soluciones

### ✅ **1. Crear un bean `ShoppingCart` con `@Scope("prototype")` y otro `ProductService` singleton que lo use.**

```java
// ShoppingCart.java
@Component
@Scope("prototype")
public class ShoppingCart {
    private final List<String> products = new ArrayList<>();

    public void addProduct(String product) {
        products.add(product);
    }

    public List<String> getProducts() {
        return products;
    }
}

// ProductService.java
@Service
public class ProductService {
    @Autowired
    private ShoppingCart shoppingCart;
    ```
    public ShoppingCart getCart() {
        return shoppingCart;
    }
}
```
## 🛠️ 2. Soluciones para corregir el comportamiento

### ✅ Opción A — `ApplicationContext` 
Inyectar el `ApplicationContext` en el singleton y pedir la instancia cada vez:

```java
@Service
public class ProductService {

    @Autowired
    private ApplicationContext context;

    public ShoppingCart createShoppingCart() {
        return context.getBean(ShoppingCart.class);
    }
}
```
### ✅ Opción B — `ObjectProvider`

Inyectar un `ObjectProvider<ShoppingCart>` y llamar `getObject()` cada vez:

```java
@Service
public class ProductService {

    @Autowired
    private ObjectProvider<ShoppingCart> shoppingCartProvider;

    public ShoppingCart createShoppingCart() {
        return shoppingCartProvider.getObject();
    }
}
```
### ✅ Opción C — `@Lookup`

Declarar un método anotado con `@Lookup` que Spring implementa en runtime:

```java
@Service
public abstract class ProductService {

    public ShoppingCart createShoppingCart() {
        return getShoppingCart();
    }

    @Lookup
    protected abstract ShoppingCart getShoppingCart();
}
```
---
### 👥 **3. Simulación de varios usuarios**


Se realiza la simulación desde la clase principal ShoppingCartApplication:

```java
@SpringBootApplication
public class ShoppingCartApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShoppingCartApplication.class, args);
        ProductService productService = context.getBean(ProductService.class);

        ShoppingCart user1Cart = productService.createShoppingCart();
        user1Cart.addProduct("Laptop");
        user1Cart.addProduct("Mouse");

        ShoppingCart user2Cart = productService.createShoppingCart();
        user2Cart.addProduct("Teléfono");
        user2Cart.addProduct("Audífonos");

        ShoppingCart user3Cart = productService.createShoppingCart();
        user3Cart.addProduct("Tablet");

        System.out.println("\n--- Contenido de los carritos ---");
        System.out.println("Usuario 1 -> " + user1Cart.getProducts());
        System.out.println("Usuario 2 -> " + user2Cart.getProducts());
        System.out.println("Usuario 3 -> " + user3Cart.getProducts());

        System.out.println("\n--- Instancias en memoria ---");
        System.out.println("Carrito Usuario 1: " + user1Cart);
        System.out.println("Carrito Usuario 2: " + user2Cart);
        System.out.println("Carrito Usuario 3: " + user3Cart);
    }
}
```
### Salida en Consola

```Java
--- Contenido de los carritos ---
Usuario 1 -> [Laptop, Mouse]
Usuario 2 -> [Teléfono, Audífonos]
Usuario 3 -> [Tablet]

--- Instancias en memoria ---
Carrito Usuario 1: dev.nelson.shoppingcart.ShoppingCart@57e95588
Carrito Usuario 2: dev.nelson.shoppingcart.ShoppingCart@4a3ac518
Carrito Usuario 3: dev.nelson.shoppingcart.ShoppingCart@22e89c21

```
---
## 🔁 4. Cambiar el `scope` a `singleton`

Con `@Scope('Singleton')`, Spring crea una única instancia compartida por todos los usuarios, por lo tanto todos los usuarios compartirán el mismo carrito y los productos se mezclarán.

---
## 📊 Comportamiento y diferencia de los Scopes Singleton y Prototype


| **Scope**   | **Descripción**                              | **Comportamiento en el ejemplo**                            |
|--------------|----------------------------------------------|--------------------------------------------------------------|
| `singleton`  | El bean se crea una sola vez por contenedor. | Todos los usuarios comparten el mismo carrito.               |
| `prototype`  | Se crea un nuevo bean cada vez que se solicita. | Cada usuario tiene su propio carrito independiente.          |

### Evidencia de las instancias distintas
```java
--- Contenido de los carritos ---
Usuario 1 -> [Laptop, Mouse]
Usuario 2 -> [Teléfono, Audífonos]
Usuario 3 -> [Tablet]

--- Instancias en memoria ---
Carrito Usuario 1: dev.nelson.shoppingcart.ShoppingCart@3852c1f8
Carrito Usuario 2: dev.nelson.shoppingcart.ShoppingCart@57e95588
Carrito Usuario 3: dev.nelson.shoppingcart.ShoppingCart@22e89c21
```
