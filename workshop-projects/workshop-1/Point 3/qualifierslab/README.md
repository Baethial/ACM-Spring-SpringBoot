# 🧩 Punto 3 – La Conspiración de los Qualifiers

## *Tema: Resolución de dependencias y ambigüedades en el contenedor IoC de Spring Boot*

---

## 🎯 **Objetivo del laboratorio**

En este ejercicio exploramos cómo el contenedor de **Inversión de Control (IoC)** de Spring resuelve **ambigüedades** cuando existen **múltiples implementaciones** de una misma interfaz.

También analizamos cómo afectan las anotaciones:

* `@Primary` 🥇
* `@Qualifier` 🎯
* `@Autowired(required = false)` 💤

Y cómo estas determinan **qué bean se inyecta**, **cuándo se inyecta**, y **qué sucede si el bean no existe**.

---

## 🧱 1. Reto 1 — Dos implementaciones, un solo contrato

Creamos la interfaz base `DiscountService` y dos implementaciones con descuentos diferentes.

### Interface base

```java
public interface DiscountService {
    double apply(double amount);
    String name();
}
```

### BasicDiscountService (10% off)

```java
@Service
// @Primary  // ← Se activará en el Reto 3
public class BasicDiscountService implements DiscountService {
    @Override public double apply(double amount) { return amount * 0.90; }
    @Override public String name() { return "BASIC"; }
}
```

### PremiumDiscountService (20% off)

```java
@Service
// @Service("vipDiscountService")  // ← Se usará en el Reto 4
public class PremiumDiscountService implements DiscountService {
    @Override public double apply(double amount) { return amount * 0.80; }
    @Override public String name() { return "PREMIUM"; }
}
```

---

## 💥 2. Reto 2 — Ambigüedad al inyectar sin especificar

El `OrderService` intenta inyectar una única instancia de `DiscountService`, pero Spring encuentra **dos candidatos** (`BasicDiscountService` y `PremiumDiscountService`).

### OrderService (versión inicial)

```java
@Service
public class OrderService {

    private final DiscountService discountService;

    public OrderService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public double checkout(double amount) {
        return discountService.apply(amount);
    }

    public String discountInUse() {
        return discountService.name();
    }
}
```

### 🖥️ Consola

```
***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of constructor in OrderService required a single bean,
but 2 were found:
    - basicDiscountService
    - premiumDiscountService

Action:

Consider marking one bean as @Primary, or use @Qualifier to identify the bean to use.
```

### ❌ Causa del error

El contenedor no puede decidir automáticamente **cuál bean inyectar** cuando existen **múltiples implementaciones del mismo tipo**.

📌 Excepción:
`org.springframework.beans.factory.NoUniqueBeanDefinitionException`

---

## 🥇 3. Reto 3 — Resolviendo con @Primary

Activamos `@Primary` en `BasicDiscountService`:

```java
@Service
@Primary
public class BasicDiscountService implements DiscountService {
    //...
}
```

### 🖥️ Consola

```
== BEANS DiscountService encontrados ==
 - com.acm.point3.discounts.BasicDiscountService
 - com.acm.point3.discounts.PremiumDiscountService
Total: 2
== DEMO PUNTO 3 ==
Estrategia en uso: BASIC
Total para 100: 90.0
```

### ✅ Explicación

* `@Primary` indica al contenedor que, si hay ambigüedad, **ese bean debe preferirse por defecto**.
* No se requiere `@Qualifier` en `OrderService`.
* El sistema arranca correctamente.

---

## 🎯 4. Reto 4 — Forzando un bean con @Qualifier

Supongamos que la tienda activa una campaña especial y quiere usar la estrategia **PREMIUM**.

Renombramos el bean o usamos su nombre por defecto, y luego lo especificamos en el constructor.

### PremiumDiscountService (renombrado)

```java
@Service("vipDiscountService")
public class PremiumDiscountService implements DiscountService {
    //...
}
```

### OrderService (forzando Premium)

```java
@Service
public class OrderService {

    private final DiscountService discountService;

    // Se comenta el constructor anterior y se activa este:
    public OrderService(@Qualifier("vipDiscountService") DiscountService discountService) {
        this.discountService = discountService;
    }

    //...
}
```

### 🖥️ Consola

```
== BEANS DiscountService encontrados ==
 - com.acm.point3.discounts.BasicDiscountService
 - com.acm.point3.discounts.PremiumDiscountService
Total: 2
== DEMO PUNTO 3 ==
Estrategia en uso: PREMIUM
Total para 100: 80.0
```

### ✅ Explicación

* `@Qualifier` **elimina la ambigüedad indicando explícitamente** qué bean debe inyectarse.
* Es útil cuando existen múltiples estrategias o configuraciones activas simultáneamente.
* Tiene **mayor prioridad que `@Primary`** si ambos están presentes.

---

## 💤 5. Reto 5 — Beans opcionales con @Autowired(required = false)

Ahora introducimos un bean opcional `SeasonalCampaign`, que **puede o no existir** en el contexto.

### Interfaz

```java
public interface SeasonalCampaign {
    double extraOff(double amount);
}
```

### Implementación (opcional)

```java
@Component   // Comentar / descomentar para observar el comportamiento
public class ChristmasCampaign implements SeasonalCampaign {
    @Override public double extraOff(double amount) { return amount * 0.95; }
}
```

### OrderService (con inyección opcional)

```java
@Autowired(required = false)
private SeasonalCampaign seasonalCampaign;

public double checkout(double amount) {
    double base = discountService.apply(amount);
    if (seasonalCampaign != null) {
        base = seasonalCampaign.extraOff(base);
    }
    return base;
}
```

### 🖥️ Consola

#### ✅ Con `@Component` activo:

```
Estrategia en uso: BASIC
Total para 100: 85.5
```

#### ⚙️ Con `@Component` comentado:

```
Estrategia en uso: BASIC
Total para 100: 90.0
```

### 🧠 Explicación

* `@Autowired(required = false)` permite que la aplicación **siga funcionando incluso si el bean no está presente**.
* Spring inyecta `null` cuando el bean no existe, evitando una excepción.
* Útil para dependencias **opcionales o condicionales**.

---

## 🧾 6. Conclusiones

| Concepto                            | Descripción                                                                                                                                  |
| ----------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------- |
| ⚙️ **Ambigüedad de beans**          | Ocurre cuando múltiples beans implementan la misma interfaz y Spring no puede decidir cuál inyectar.                                         |
| 🥇 **@Primary**                     | Indica el bean predeterminado cuando existen múltiples candidatos. Se usa para definir una política o implementación general.                |
| 🎯 **@Qualifier("nombre")**         | Especifica explícitamente qué bean usar, incluso si hay un `@Primary`. Útil en contextos con varias estrategias.                             |
| 💤 **@Autowired(required = false)** | Permite inyectar un bean de forma opcional. Si el bean no existe, Spring asigna `null` sin fallar.                                           |
| 🧠 **Buenas prácticas**             | Usar `@Qualifier` en servicios de propósito específico y `@Primary` para una implementación por defecto. Evitar ambigüedades explícitamente. |

---

## 💬 Reflexión final

Entender cómo Spring **resuelve dependencias** es esencial para diseñar aplicaciones modulares, flexibles y mantenibles.
El uso consciente de `@Primary`, `@Qualifier` y `@Autowired(required=false)` te permite controlar **qué bean se inyecta, cuándo y bajo qué condiciones**.

🧠 En otras palabras: el contenedor IoC no “adivina” tus intenciones — **tú debes guiarlo con precisión.**