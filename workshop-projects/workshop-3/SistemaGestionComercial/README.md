# 📦 README — Sistema de Gestión Comercial

> **Versión:** 1.0 · **Idioma:** Español

---

## 🗂 Contenido principal del repositorio

* `SistemaGestionComercial/` — 📁 **Carpeta** que contiene el **proyecto Spring Boot** (backend).
* `QueryImplementationAnalysis.pdf` — 📄 **Documento PDF** con la explicación detallada de las **14 consultas complejas** del proyecto.
* `SistemaGestionComercial API.postman_collection.json` — 🔁 **Colección Postman** donde se probaron todos los endpoints de la API.

---

## 🧭 Descripción

Este repositorio contiene el **backend** para el *Sistema de Gestión Comercial*, implementado con **Spring Boot**. Además incluye documentación y pruebas (Postman) que explican y validan el comportamiento de las consultas más complejas del sistema.

---

## ✅ Resumen rápido

* Proyecto: Spring Boot (Java).
* Documentación técnica: `QueryImplementationAnalysis.pdf` (14 consultas complejas).
* Pruebas/recorridos de endpoints: archivo Postman (`.json`).

---

## ⚙️ Requisitos previos

* JDK 21+. ☑️
* Maven. 🛠️
* Postman (para importar y ejecutar la colección). 🔁

---

## 📁 Estructura sugerida (resumen)

```
/
SistemaGestionComercial/
  ├─ src/
  ├─ pom.xml
  └─ README.md
  └─ QueryImplementationAnalysis.pdf
  └─ SistemaGestionComercial API.postman_collection.json

```

---

## 🚀 Cómo ejecutar el proyecto (rápido)

1. Abrir terminal en `SistemaGestionComercial/`.
2. Compilar:

```bash
# si usa Maven
mvn clean package

# si usa Gradle
./gradlew build
```

3. Ejecutar:

```bash
# Maven
mvn spring-boot:run

# o ejecutar el jar
java -jar target/nombre-del-proyecto.jar
```

4. Verificar que la API esté disponible en `http://localhost:8080/` (revisar `application.properties` para puerto).

---

## 🧪 Probar los endpoints con Postman

1. Importa `SistemaGestionComercial API.postman_collection.json` en Postman.
2. Asegúrate de configurar variables de entorno (si aplica): `base_url`, `token`, etc.
3. Ejecuta las colecciones/requests — la colección incluye pruebas para todos los endpoints del sistema.

---

## 📚 Documentación de consultas complejas

* El archivo `QueryImplementationAnalysis.pdf` contiene la **explicación detallada** y el **análisis** de las **14 consultas** más complejas del proyecto (enfoque, SQL/HQL/JPQL utilizado, ejemplos de entrada/salida y casos borde).
* Recomendación: leer ese PDF junto con el código fuente para entender las decisiones de implementación y optimizaciones aplicadas.

---

## 📝 Buenas prácticas y notas

* Evita ejecutar en producción sin revisar `application.properties` (credenciales, URLs, puertos). 🔒
* Si el proyecto usa migraciones DB, revisa si hay scripts pendientes antes de iniciar. 🗃️
* Añade tus propios environment variables en Postman para facilitar la ejecución de la colección. ⚙️

---
