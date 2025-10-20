<div align="center">
<h1>☕ ACM GiWeb — Java Backend Spring Boot</h1> 
</div>
<div align="center">

<img src="https://www.acmud.org/acmud.webp" width="120" alt="ACM Logo"/>
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="120" alt="Java Logo"/>
<img src="https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg" width="200" alt="Spring Logo"/>

### 🎓 Facultad de Ingeniería — Universidad Distrital Francisco José de Caldas  
### 👩‍💻 Grupo GiWeb — ACM Student Chapter  

---

## 🧩 Talleres: 
### 1. Comportamientos del IoC en Spring Framework
**Repositorio:** `ACM-Spring-SpringBoot`  
**Ubicación:** `/workshop-projects/workshop-1/`  
**Lenguaje:** Java ☕  
**Frameworks:** Spring Framework 🌱 y Spring Boot 🚀  

---

</div>

## 🧭 Introducción

El contenedor de **Inversión de Control (IoC Container)** de Spring administra los objetos (*beans*) de una aplicación, controlando su creación, ciclo de vida, dependencias y visibilidad.

Sin embargo, estos beans no siempre se comportan como se espera.  
A través de estos **cuatro talleres**, analizamos escenarios reales donde los comportamientos del contenedor generan resultados inesperados —permitiéndote desarrollar una comprensión profunda del motor interno de Spring.

---

## 🎯 Objetivos de Aprendizaje

✅ Comprender cómo se **crean, gestionan y destruyen los beans** dentro del contenedor IoC.  
✅ Diferenciar los efectos de las anotaciones `@Component`, `@Service`, `@Repository`, `@Configuration` y `@Bean`.  
✅ Analizar los **scopes** y su impacto (`singleton`, `prototype`, `request`, `session`).  
✅ Resolver **conflictos de inyección**, **dependencias circulares** y **ambigüedades**.  
✅ Explicar comportamientos inesperados como `lazy loading` inefectivo o duplicación de beans.  
✅ Documentar hallazgos, evidencias y soluciones aplicadas.

---

## 🧩 Puntos Incuidos

| 🧠 Punto | Tema | Proyecto | Descripción |
|:--:|:--|:--|:--|
| **1** | 🔬 *Creación, inyección y ciclo de vida de beans* | `beanlab` | Experimenta con la creación y carga de beans mediante `@Component`, `@Bean`, y `@Lazy`. |
| **2** | 🛒 *Scopes y gestión de instancias* | `shoppingcart` | Simula varios usuarios con diferentes *scopes* y analiza la reutilización de instancias. |
| **3** | 🎯 *Resolución de dependencias y ambigüedades* | `qualifierslab` | Comprueba cómo Spring resuelve conflictos entre múltiples implementaciones de una misma interfaz. |
| **4** | 🔁 *Dependencias circulares y orden de inicialización* | `infiniteloop` | Analiza un error de ciclo de dependencias y cómo romperlo mediante `@Lazy`, `@Setter`, o rediseño. |

---

## 🧮 Criterios de Evaluación

| Criterio | Descripción | Puntaje |
|:--|:--|:--:|
| 🧠 **Análisis técnico** | Identifica correctamente el comportamiento inesperado y sus causas. | 10 pts |
| 💻 **Implementación** | Reproduce los escenarios propuestos demostrando comprensión del contenedor. | 15 pts |
| 🧩 **Solución y justificación** | Propone soluciones adecuadas en código, configuración o diseño. | 15 pts |
| 📝 **Documentación y presentación** | Presenta README claro, evidencias y reflexión final. | 10 pts |

---

## 🧱 Estructura del Repositorio

```bash
ACM-Spring-SpringBoot/
└── workshop-projects/
    └── workshop-1/
        ├── Point 1/
        │   └── beanlab/
        ├── Point 2/
        │   └── shoppingcart/
        ├── Point 3/
        │   └── qualifierslab/
        └── Point 4/
            └── infiniteloop/
````

Cada carpeta contiene:

* Código fuente (`src/main/java`)
* Recursos (`src/main/resources`)
* Documentación individual (`README.md` por punto)

---

## ⚙️ Requisitos Técnicos

* **Java:** 17 o superior ☕
* **Spring Boot:** 3.x 🌱
* **Maven:** 3.8+ 🧰
* **IDE recomendado:** IntelliJ IDEA

---

## 🧠 Conceptos Clave Explorados

| Concepto                               | Descripción Breve                                                                                   |
| :------------------------------------- | :-------------------------------------------------------------------------------------------------- |
| **IoC (Inversion of Control)**         | Principio que invierte la responsabilidad de creación de objetos, delegándola al contenedor Spring. |
| **DI (Dependency Injection)**          | Mecanismo de inyección automática de dependencias entre beans.                                      |
| **Scopes**                             | Determinan la cantidad y duración de instancias gestionadas (`singleton`, `prototype`, etc.).       |
| **@Primary / @Qualifier**              | Controlan qué implementación se inyecta en caso de ambigüedad.                                      |
| **@Lazy / @Autowired(required=false)** | Controlan cuándo y cómo se inicializan dependencias.                                                |
| **Circular References**                | Ocurren cuando dos beans dependen mutuamente; requieren rediseño o uso de `@Lazy`.                  |

---

## 🧑‍💻 Desarrollado por

| Rol                              | Nombre                                                       | GitHub / Contacto             |
| :------------------------------- | :----------------------------------------------------------- | :---------------------------- |
| 🧑‍💻 Estudiante / Desarrollador | *Nelson Navarro* | [GitHub]([https://github.com/](https://github.com/Nelsonn-bit)) |
| 🧑‍💻 Estudiante / Desarrollador | *Ianjaner Alfonso Beltran Guañarita* | [GitHub]([https://github.com/](https://github.com/ianjaner75)) |
| 🧑‍💻 Estudiante / Desarrollador | *Jorge Andrés Quiceno Sanabria* | [GitHub]([https://github.com/](https://github.com/Baethial)) |


---

## 🚀 Ejecución

Ejecuta cualquiera de los proyectos desde su directorio:

```bash
cd workshop-projects/workshop-1/Point\ 3/qualifierslab
mvn spring-boot:run
```

---

## 📚 Créditos

Proyecto académico del **Grupo GiWeb - ACM UD**
Facultad de Ingeniería, **Universidad Distrital Francisco José de Caldas**
Curso: *Java Backend con Spring Framework y Spring Boot*

---

<div align="center">

💡 *“Comprender cómo el contenedor IoC piensa, es el primer paso para dominar Spring.”* 🌱 <br/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" width="100"/>

</div>
```
