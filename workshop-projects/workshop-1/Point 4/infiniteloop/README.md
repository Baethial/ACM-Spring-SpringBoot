# ⚙️ Punto 4 – El Bucle Infinito

## *Tema: Dependencias circulares, orden de inicialización y contexto de carga en Spring Boot*

---

## 🎯 **Escenario**

En este laboratorio analizamos un caso clásico de error en el contenedor de **Inversión de Control (IoC)**:
dos beans que dependen mutuamente entre sí.

👉 **InventoryService** depende de **OrderService** para verificar ventas.
👉 **OrderService** depende de **InventoryService** para validar stock.

El objetivo es observar:

1. Qué ocurre con la inyección por constructor.
2. Qué pasa si cambiamos a inyección por setter.
3. Cómo afecta el orden de inicialización de los beans.
4. Y finalmente, **cómo resolver el ciclo** correctamente.

---

## 🧩 1. Escenario base — Inyección por Constructor

### 📦 InventoryService

```java
@Service
public class InventoryService {

    private final OrderService orderService;

    @Autowired
    public InventoryService(OrderService orderService) {
        this.orderService = orderService;
        System.out.println("InventoryService initializing...");
    }

    public void checkStock() {
        System.out.println("Checking stock...");
    }
}
```
### 🧱 OrderService

```java
@Service
public class OrderService {

    private final InventoryService inventoryService;

    @Autowired
    public OrderService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        System.out.println("OrderService initializing...");
    }

    public void processOrder() {
        System.out.println("Processing order...");
    }
}
```

### 🖥️ Consola

```
***************************
APPLICATION FAILED TO START
***************************

Description:

The dependencies of some of the beans in the application context form a cycle:

┌─────┐
|  inventoryService defined in file [C:\Users\Andre\OneDrive\Documents\Universidad\2025-III\ACMSpring&SpringBoot\workshop-projects\workshop-1\Point 4\infiniteloop\target\classes\dev\jaqs\infiniteloop\service\InventoryService.class]
↑     ↓
|  orderService defined in file [C:\Users\Andre\OneDrive\Documents\Universidad\2025-III\ACMSpring&SpringBoot\workshop-projects\workshop-1\Point 4\infiniteloop\target\classes\dev\jaqs\infiniteloop\service\OrderService.class]
└─────┘


Action:

Relying upon circular references is discouraged and they are prohibited by default. Update your application to remove the dependency cycle between beans. As a last resort, it may be possible to break the cycle automatically by setting spring.main.allow-circular-references to true.


Process finished with exit code 1
```

### 💥 ¿Qué pasó?

El contenedor de Spring intenta crear ambos beans al mismo tiempo, pero:

* `InventoryService` necesita un `OrderService` completamente inicializado.
* `OrderService` necesita un `InventoryService` completamente inicializado.
* ❌ **Ninguno puede completarse primero**, creando un bucle infinito.

Spring detecta este ciclo durante la carga del contexto y lanza:

```
Exception encountered during context initialization - cancelling refresh attempt: 
org.springframework.beans.factory.UnsatisfiedDependencyException: 
Error creating bean with name 'inventoryService' defined in file [C:\Users\Andre\...]: 
Unsatisfied dependency expressed through constructor parameter 0: 
Error creating bean with name 'orderService' defined in file [C:\Users\Andre\...]: 
Unsatisfied dependency expressed through constructor parameter 0: 
Error creating bean with name 'inventoryService': Requested bean is currently in creation: 
Is there an unresolvable circular reference or an asynchronous initialization dependency?
```

A partir de **Spring Boot 2.6**, las referencias circulares están **deshabilitadas por defecto**, promoviendo un diseño más limpio.

---

## 🔁 2. Intento de Solución — Inyección por Setter

### 📦 InventoryService ()

```java
@Service
public class InventoryService {

    private final OrderService orderService;

    @Autowired
    public InventoryService(OrderService orderService) {
        this.orderService = orderService;
        System.out.println("InventoryService initializing...");
    }

    public void checkStock() {
        System.out.println( "Checking stock...");
    }
}
```

### 🧱 OrderService (modificado)

```java
@Service
public class OrderService {

    private InventoryService inventoryService;
    
    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        System.out.println("OrderService initializing using setter injection...");
    }

    public void processOrder() {
        System.out.println("Processing order...");
    }
}
```

### 🖥️ Consola

```
***************************
APPLICATION FAILED TO START
***************************

Description:

The dependencies of some of the beans in the application context form a cycle:

┌─────┐
|  inventoryService defined in file [C:\Users\Andre\OneDrive\Documents\Universidad\2025-III\ACMSpring&SpringBoot\workshop-projects\workshop-1\Point 4\infiniteloop\target\classes\dev\jaqs\infiniteloop\service\InventoryService.class]
↑     ↓
|  orderService
└─────┘


Action:

Relying upon circular references is discouraged and they are prohibited by default. Update your application to remove the dependency cycle between beans. As a last resort, it may be possible to break the cycle automatically by setting spring.main.allow-circular-references to true.


Process finished with exit code 1
```

### ⚠️ Análisis

Aunque cambiamos a **inyección por setter**, Spring Boot **sigue detectando la referencia circular**.

📌 **Por qué:**

* El ciclo aún existe conceptualmente.
* Spring debe construir ambos beans antes de llamar a los setters.
* Como las referencias circulares están bloqueadas (`spring.main.allow-circular-references=false`), la app no inicia.

---

## 🧠 3. Soluciones y Alternativas


### ⚙️ **Opción 1: Permitir referencias circulares (no recomendado)**

En `application.properties`:

```properties
spring.main.allow-circular-references=true
```

🟢 La aplicación inicia,
pero ❗ esta opción **solo debe usarse temporalmente** durante depuración o pruebas.

> ⚠️ Oculta un problema de diseño y puede causar fallas lógicas en producción.

---

### ✅ **Opción 2: Romper el ciclo con `@Lazy`**

```java
@Service
public class OrderService {

    private InventoryService inventoryService;

    @Autowired
    public OrderService(@Lazy InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        System.out.println("OrderService initializing...");
    }

    public void processOrder() {
        System.out.println("Processing order...");
    }
}
```

### 🖥️ Consola

```
...
OrderService initializing...
InventoryService initializing...
2025-10-19T18:38:53.684-05:00  INFO 860 --- [infiniteloop] [           main] d.j.i.InfiniteLoopApplication            : Started InfiniteLoopApplication in 1.358 seconds (process running for 2.125)

Process finished with exit code 0
```

### ✨ Explicación

* `@Lazy` le indica al contenedor que cree un **proxy temporal**,
  retrasando la instanciación del `OrderService` hasta que realmente se use.
* Esto **rompe el ciclo de creación** sin modificar demasiado el diseño.

---

### 🧱 **Opción 3: Refactorizar el diseño (solución ideal)**

Rompe la dependencia mutua creando un tercer servicio que encapsule la lógica compartida:

```java
@Service
public class SalesValidator {
    public boolean validateStockAndOrder(/*...*/) {
        // ...
    }
}

@Service
public class InventoryService {
    private final SalesValidator salesValidator;
    // ...
}

@Service
public class OrderService {
    private final SalesValidator salesValidator;
    // ...
}
```

💡 Con esto:

* No hay dependencias cruzadas.
* Mejora la **cohesión** y la **separación de responsabilidades**.
* Facilita las pruebas unitarias e integración.

---

## 🧩 4. Orden de Inicialización y Contexto de Carga

Durante el arranque de Spring Boot:

1. El **ApplicationContext** analiza las definiciones de beans.
2. Intenta resolver las dependencias (por tipo y nombre).
3. Si detecta un ciclo en el grafo de dependencias, lanza la excepción.
4. Con `@Lazy`, el ciclo se pospone y la creación se completa con éxito.

🕓 Ejemplo de orden observado:

```
...
OrderService initializing...
InventoryService initializing...
2025-10-19T18:38:53.684-05:00  INFO 860 --- [infiniteloop] [           main] d.j.i.InfiniteLoopApplication            : Started InfiniteLoopApplication in 1.358 seconds (process running for 2.125)

Process finished with exit code 0
```


🔍 Análisis técnico:

Cuando aplicas @Lazy a una dependencia, no se cambia directamente el orden de inicialización de los beans, sino que:

* Spring retrasa (no invierte) la creación del bean anotado con @Lazy.
* El bean que depende de la instancia marcada como @Lazy sí se crea primero, pero la dependencia no se materializa aún (Spring inyecta un proxy en su lugar).
* Ese bean “perezoso” se crea solo cuando se accede por primera vez a su método o propiedad.
* Por tanto, no es que el orden se modifique, sino que una de las instancias se difiere en su inicialización.

---

## 🧾 5. Conclusiones

| Aspecto                                 | Descripción                                                                                        |
|-----------------------------------------|----------------------------------------------------------------------------------------------------|
| ⚙️ **Motivo del error**                 | El contenedor IoC no puede crear beans con dependencias circulares simultáneas.                    |
| 🧱 **Inyección por constructor**        | Falla siempre ante ciclos, ya que exige instanciación inmediata.                                   |
| 🧩 **Inyección por setter**             | No elimina el ciclo; solo cambia el punto donde ocurre.                                            |
| 🚫 **`allow-circular-references=true`** | Solo como último recurso, no recomendado para producción.                                          |
| 💤 **Uso de `@Lazy`**                   | Permite romper el ciclo creando un proxy diferido.                                                 |
| 💤 **Inicialización con @Lazy**         | La creación del bean anotado con @Lazy se difiere hasta que se necesite por primera vez. Como resultado, el otro bean involucrado en la dependencia se inicializa primero, y el bean perezoso se crea posteriormente bajo demanda. |
| 🧠 **Solución ideal**                   | Refactorizar el diseño y eliminar la dependencia mutua.                                            |

---

## 💬 Reflexión final

Comprender el **orden de inicialización de beans** y el **funcionamiento del IoC container** es esencial para evitar errores de diseño y dependencias cíclicas.
La modularización, la cohesión alta y el acoplamiento bajo son pilares de un sistema **mantenible, testeable y robusto**. 🧠💪
