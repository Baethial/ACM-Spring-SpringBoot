# ❄️🍁API Reactiva de Pronóstico del Clima 🌤️🌨️
## *Tema: Spring WebFlux, WebClient, Reactividad y Agregación de Datos*

---

## 🚩☀️ **Objetivo del Proyecto**

Este proyecto es un microservicio **Spring Boot** diseñado para:

1. Consumir datos del API externo de OpenWeatherMap de forma **no bloqueante**.

2. Procesar y agregar los datos horarios (72 horas / 3 horas) en un resumen conciso.

3. Exponer el resultado final a través de un **REST API** limpio, manteniendo un flujo de trabajo reactivo de punta a punta.

---

##  1. Conceptos Clave y Tecnologías Aplicadas

### 1.1 Spring WebFlux y Programación Reactiva

La aplicación utiliza **Spring WebFlux** sobre un servidor no bloqueante (Netty por defecto).

* **Flujo Reactivo:** A diferencia del modelo tradicional (un hilo por solicitud), WebFlux usa un pool de hilos pequeño para manejar un gran volumen de concurrencia, lo que aumenta la **escalabilidad** y la **eficiencia** bajo carga.

* **Contratos Reactivos:** La capa de Servicio y Controlador manejan tipos `Mono`, asegurando que el flujo de datos permanezca no bloqueante hasta que la respuesta se envíe al cliente.

### 1.2 Cliente HTTP No Bloqueante: WebClient

Utilizamos **WebClient** para realizar la llamada al API externo, un componente esencial en cualquier aplicación WebFlux. Este es el principal beneficio sobre el antiguo `RestTemplate`.

| Característica | WebClient (Reactivo) | RestTemplate (Tradicional) | 
| :--- | :--- | :--- | 
| **Modelo I/O** | No bloqueante | Bloqueante | 
| **Concurrencia** | Altamente escalable (usa pocos hilos) | Un hilo por solicitud (puede agotar recursos) | 
| **Respuesta** | Retorna `Mono` o `Flux` | Retorna objeto crudo | 

### 1.3 Autenticación con Clave API (Seguridad)

El acceso al API externo se maneja mediante una clave de autenticación.

1. **Configuración:** La clave se guarda en el archivo **`application.properties`**: `openweathermap.api.key=...`

2. **Inyección:** Se inyecta de forma segura en la capa de servicio (`ForecastService`) mediante la anotación `@Value`.

3. **Uso:** Se pasa como parámetro de consulta (`appid`) en la construcción de la URI del `WebClient`.

---

## 📚📝 2. Manejo y Transformación de Datos

### 2.1 Consumo de JSON Complejo

El JSON de respuesta de OpenWeatherMap es jerárquico. Para mapearlo a objetos Java:

* **POJOs Anidados:** El modelo principal (`ForecastResponse`) utiliza clases estáticas anidadas (`WeatherResponse`, `Main`, `Weather`) para replicar fielmente la estructura del JSON.

* **Mapeo de Nombres:** Utilizamos **`@JsonAlias`** para resolver la diferencia entre el formato *snake_case* del JSON (`feels_like`, `dt_txt`) y el *camelCase* de Java (`feelsLike`, `timeStamp`).

### 2.2 Agregación de Datos con Java Streams

La lógica de negocio reside en el `ForecastService`, cuya función principal es transformar la lista de 3-hourly reports en un `ForecastSummaryDTO` limpio.

* **Cálculo de Promedios:** Se utiliza `stream().mapToDouble().average()` para calcular la temperatura promedio de 24 horas y la temperatura promedio diaria.

* **Determinación de la Condición Dominante (Moda):** Se utiliza `Collectors.groupingBy()` y `Collectors.counting()` para encontrar la descripción meteorológica más frecuente ("Rain", "Clouds") en un periodo dado.

* **Límites de Días:** Los reportes se agrupan por `LocalDate` para obtener la vista de calendario correcta, y se aplica **`.limit(3)`** para asegurar un resumen estricto de los próximos tres días.

---

## 🛠️🧩 3. Estructura y Componentes del Proyecto

| Componente                   | Capa | Función Principal | 
|:-----------------------------| :--- | :--- | 
| **🕹️ `ForecastController`** | Presentación (HTTP) | Define el endpoint `GET /api/forecast?city={name}` y delega al servicio. | 
| **🤖 `ForecastService`**     | Negocio / Lógica | Contiene la lógica de agregación (24h y 3 días) y la llamada reactiva con `WebClient`. | 
| **💾 `ForecastResponse`**    | Modelo (Contrato Externo) | Deserializa la respuesta **completa y cruda** del API de OpenWeatherMap. | 
| **🗺️ `ForecastSummaryDTO`**  | Modelo (Contrato API Interno) | El objeto de salida **agregado y simplificado** que el API expone al usuario. | 
| **⚙️ `WebClientConfig`**     | Configuración | Crea y configura el `WebClient` con la URL base de OpenWeatherMap para la inyección de dependencias. | 

---

## 💥🚀 4. Puesta en Marcha

### Requisitos Previos

* Kit de Desarrollo de Java (JDK) 17+

* Un IDE (IntelliJ, VS Code, etc.)

* Una Clave API válida de OpenWeatherMap.

### Configuración y Ejecución

1. **Configurar Clave API:** Abra `src/main/resources/application.properties` y reemplace el marcador de posición con su clave:

```

openweathermap.api.key=YOUR\_ACTUAL\_API\_KEY\_HERE

```

2. **Ejecutar:** Inicie la clase principal de la aplicación Spring Boot:

```

./mvnw spring-boot:run

```

### Ejemplo de Uso

Una vez que la aplicación esté en funcionamiento (por defecto en `http://localhost:8080`), se puede consultar el resumen del pronóstico:

```

curl -X GET "http://localhost:8080/api/forecast?city=London"

```

**Ejemplo de Respuesta (JSON):**

```

{
"next24hSummary": {
"averageTemperature": 10.6,
"generalWeatherDescription": "Rain",
"lastUpdateTime": "2025-11-02 18:00:00"
},
"threeDaySummary": [
{
"date": "2025-11-01",
"averageTemperature": 11.1,
"dominantWeather": "Rain"
},
{
"date": "2025-11-02",
"averageTemperature": 10.3,
"dominantWeather": "Clouds"
},
{
"date": "2025-11-03",
"averageTemperature": 13.8,
"dominantWeather": "Clouds"
}
]
}

```

---

## 💎⭐ Conclusiones Clave

| Aspecto | `WebClient` vs. `RestTemplate` | Beneficio | 
| :--- | :--- | :--- | 
| **Modelo I/O** | `WebClient` es No Bloqueante | Alta concurrencia y escalabilidad. | 
| **Seguridad** | API Key inyectada con `@Value` | Separación de secretos de la lógica de negocio. | 
| **JSON** | Mapeo con POJOs y `@JsonAlias` | Conversión limpia de *snake_case* a *camelCase*. | 
| **Servicio** | Agregación con Java Streams | Código conciso y eficiente para promedios y moda. | 
| **API** | Expone un **DTO** | Mantiene un un contrato API estable, independiente del servicio externo. | 

💡🧐 **Reflexión:** El uso de Spring WebFlux y WebClient garantiza que la aplicación no solo cumpla su función, sino que lo haga de manera eficiente y moderna, manteniendo la **reactividad** en cada capa del servicio.
