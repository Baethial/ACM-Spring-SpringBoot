# 🧪 Punto 1 - El Laboratorio de los Beans

## *Tema: Creación, inyección y ciclo de vida de beans*

Este laboratorio tiene como propósito **ilustrar el comportamiento del contenedor de Inversión de Control (IoC)** de Spring y **cómo se gestionan los beans** dependiendo de cómo son definidos: mediante anotaciones (`@Component`) o de forma manual a través de clases de configuración (`@Configuration` y `@Bean`).

También se analiza el **ciclo de vida de los beans** y el efecto del uso de la anotación `@Lazy` sobre la creación de instancias.

---

## 🔹 1. Bean con el mismo nombre en la clase y en la clase de configuración

### Clase `ExperimentService`

```java
@Component("nombreBean")
public class ExperimentService {

    public ExperimentService() {
        System.out.println("ExperimentService Bean created");
    }
}
```

### Clase de Configuración

```java
@Configuration
public class ExperimentServiceConfig {

    @Bean("nombreBean")
    @Lazy
    public ExperimentService experimentService() {
        System.out.println("Created from configuration class");
        return new ExperimentService();
    }
}
```

### Punto de Acceso de la Aplicación

```java
@SpringBootApplication
public class BeanlabApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanlabApplication.class, args);
    }

//    @Bean
//    ApplicationRunner runner(@Qualifier("nombreBean") ExperimentService experimentService) {
//        return args -> {
//            System.out.println("Starting application");
//        };
//    }
}
```

### Salida en Consola

```
...
Created from configuration class
ExperimentService Bean created
...

Process finished with exit code 0
```

### 🧩 Análisis y Conclusiones

* ✅ **Se crea una sola instancia del Bean.**
  Aunque el bean está declarado tanto con `@Component` como con `@Bean`, Spring detecta el conflicto y **prioriza la definición manual** en la clase de configuración.

* ⚙️ **Prioridad del Bean definido en clase de configuración.**
  Cuando dos beans comparten el mismo nombre, **Spring considera la definición dentro de la clase `@Configuration` como dominante**, y omite la versión `@Component`.

* ⚠️ **No se genera excepción por duplicidad de nombre.**
  Spring Boot maneja la situación de forma silenciosa, reemplazando la definición automática (`@Component`) por la manual (`@Bean`).

---

## 🔹 2. Usando la anotación `@Lazy` en la clase de configuración

### Clase de Configuración

```java
@Configuration
public class ExperimentServiceConfig {

    @Bean("nombreBean")
    @Lazy
    public ExperimentService experimentService() {
        System.out.println("Created from configuration class");
        return new ExperimentService();
    }
}
```

### Salida en Consola

```
...

Process finished with exit code 0
```

### 🧩 Análisis y Conclusiones

* 💤 **El bean no se crea durante el arranque de la aplicación.**
  La anotación `@Lazy` en una definición `@Bean` **retrasa la creación del objeto hasta que sea realmente solicitado** por el contenedor o inyectado en otro bean.

* ⚙️ **Persisten las reglas de prioridad.**
  A pesar de que el bean no se inicializa inmediatamente, Spring sigue **considerando válida la definición manual** (en la clase `@Configuration`) por encima de la anotación `@Component`.

* 💡 **El ciclo de vida del bean se difiere.**
  En este caso, el bean permanecerá en estado “pendiente” dentro del contexto de Spring y **no se instanciará hasta que se invoque explícitamente** desde otra clase o componente.

---

## 🔹 3. Usando la anotación `@Lazy` en la clase `ExperimentService`

### Clase `ExperimentService`

```java
@Component("nombreBean")
@Lazy
public class ExperimentService {

    public ExperimentService() {
        System.out.println("ExperimentService Bean created");
    }
}
```

### Salida en Consola

```
...
Created from configuration class
ExperimentService Bean created
...

Process finished with exit code 0
```

### 🧩 Análisis y Conclusiones

* ✅ **El bean sí se crea.**
  Aunque la clase está marcada con `@Lazy`, la presencia del bean con el mismo nombre en la clase de configuración hace que **Spring use la versión definida manualmente** y **termine creando el bean** durante el arranque (a menos que también esté marcada con `@Lazy`).

* ⚙️ **Prioridad de la configuración manual.**
  Como en los casos anteriores, Spring considera **la definición del bean en la clase `@Configuration` como prioritaria**, incluso si el componente también está definido con `@Component`.

* 🧠 **El `@Lazy` en la clase `@Component` no tiene efecto si el bean es sobrescrito.**
  Dado que el bean `nombreBean` fue redefinido manualmente, **la configuración de pereza en la clase original no afecta el comportamiento final.**

---

## 🔄 Ciclo de Vida de un Bean en Spring

1. **Instanciación:**
   El contenedor de Spring crea el objeto (o lo posterga si está marcado con `@Lazy`).

2. **Inyección de dependencias:**
   Spring resuelve e inyecta las dependencias necesarias (`@Autowired`, `@Qualifier`, etc.).

3. **Inicialización:**
   Se ejecutan métodos marcados con `@PostConstruct` o los definidos mediante `initMethod`.

4. **Uso del bean:**
   El bean queda disponible dentro del contexto de Spring.

5. **Destrucción:**
   Al cerrar el contexto de la aplicación, Spring ejecuta los métodos marcados con `@PreDestroy` o configurados mediante `destroyMethod`.

---

## ⚖️ Diferencias entre Beans creados manualmente y Beans automáticos

| Característica             | `@Component`                                                             | `@Bean` en `@Configuration`                                                         |
| -------------------------- | ------------------------------------------------------------------------ | ----------------------------------------------------------------------------------- |
| **Definición**             | Automática por escaneo de paquetes (`@ComponentScan`)                    | Manual dentro de una clase `@Configuration`                                         |
| **Control del nombre**     | Opcional (por defecto el nombre es el de la clase con minúscula inicial) | Total (se puede definir explícitamente el nombre)                                   |
| **Prioridad**              | Baja (puede ser sobrescrito por un `@Bean` con el mismo nombre)          | Alta (sobrescribe a un `@Component`)                                                |
| **Configuración avanzada** | Limitada                                                                 | Permite personalizar dependencias, ciclo de vida y alcance                          |
| **Lazy Initialization**    | Se aplica al componente individual                                       | Puede aplicarse globalmente o a nivel de método                                     |
| **Uso recomendado**        | Clases simples con comportamiento autónomo                               | Casos donde se requiere control detallado o beans externos (por ejemplo, librerías) |

---

## 🧾 Conclusión General

Este laboratorio demuestra cómo el **contenedor de IoC de Spring** gestiona y prioriza los beans según su origen.
En resumen:

* Spring **detecta y evita conflictos de nombres** entre beans.
* Los **beans definidos manualmente** (en clases `@Configuration`) **tienen prioridad** sobre los automáticos (`@Component`).
* El uso de `@Lazy` **controla el momento de la instanciación**, pero **no altera la prioridad de las definiciones**.
* Comprender estos comportamientos es esencial para construir aplicaciones **predecibles, eficientes y libres de conflictos de inyección.**

---
