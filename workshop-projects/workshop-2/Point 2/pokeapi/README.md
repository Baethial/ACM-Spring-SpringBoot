# PokeAPI Spring WebFlux – Taller 2 (ACM / Spring Boot)

Proyecto desarrollado como parte del Taller 2 del curso de Spring, cuyo objetivo es construir un microservicio reactivo que consuma la API pública de Pokémon (**https://pokeapi.co**) utilizando **Spring Boot + WebFlux + WebClient**.

Este proyecto NO usa `RestTemplate` ni `spring-boot-starter-web`, ya que fue diseñado 100% reactivo siguiendo las indicaciones del taller.

---

## ✅ Características principales

| Funcionalidad | Descripción |
|--------------|-------------|
| Cliente HTTP reactivo | Implementado con `WebClient` |
| API REST propia | Expone 3 endpoints GET |
|  Mapeo parcial del JSON de PokeAPI | name, id, weight, height, abilities |
| Versión Slim DTO | Solo retorna nombre, peso y habilidades |
|  Validación de parámetros | Uso de `@NotBlank` |
|  Documentación automática | Swagger UI disponible |
|  Compatible con Java 21 y Spring Boot 3.5.x |

---

## Arquitectura de paquetes

dev.jaqs.pokeapi
│
├── controller # Controlador REST
├── service # Lógica de negocio y WebClient
├── model # Modelos (mapeo del JSON original)
├── dto # Objetos de transferencia (Slim DTO)
└── config # Configuración del WebClient

yaml
Copiar código

---

##  Endpoints disponibles

###  1. Obtener Pokémon completo

GET /api/pokemon/{name}


Copiar código

Ejemplo:

http://localhost:8080/api/pokemon/pikachu


Copiar código

 Respuesta (recortada para ejemplo):

```json
{
  "name": "pikachu",
  "id": 25,
  "weight": 60,
  "height": 4,
  "abilities": [
    { "ability": { "name": "static" } },
    { "name": "lightning-rod" }
  ]
}
2. Obtener versión slim (DTO)
pgsql
Copiar código
GET /api/pokemon/{name}?view=slim
 Ejemplo:

bash
Copiar código
http://localhost:8080/api/pokemon/pikachu?view=slim
 Respuesta:

json
Copiar código
{
  "name": "pikachu",
  "weight": 60,
  "abilities": [
    "static",
    "lightning-rod"
  ]
}
 3. Alias directo a la versión slim
bash
Copiar código
GET /api/pokemon/{name}/basic
 Ejemplo:

bash
Copiar código
http://localhost:8080/api/pokemon/pikachu/basic
 Misma salida del Slim DTO.

 Pruebas en Postman
Método	URL
GET	http://localhost:8080/api/pokemon/pikachu
GET	http://localhost:8080/api/pokemon/pikachu?view=slim
GET	http://localhost:8080/api/pokemon/pikachu/basic

No requiere headers, body ni autenticación.

📄 Swagger UI (Documentación API)
Una vez corriendo el proyecto, acceder a:

bash
Copiar código
http://localhost:8080/swagger-ui.html
El JSON OpenAPI (especificación) está en:

bash
Copiar código
http://localhost:8080/v3/api-docs
⚙️ Tecnologías utilizadas
Componente	Versión
Java	21
Spring Boot	3.5.7
Spring WebFlux	
WebClient	
springdoc-openapi	2.8.5

🧾 Notas del taller

No se usó RestTemplate
No se usó spring-boot-starter-web
Se implementó PokeAPI v2
DTO slim implementado
Validación de errores con ResponseStatusException
Swagger UI funcional