# Max-Integer-Inclusion-Cloud-BACK

## Descripción
Es una aplicación Spring Boot desarrollada para calcular el maximo numero  dado `x`, `y` y `n` y mostrar el valor calculado k a través de un  API REST.

## Características
- **Spring Boot 3.2.1**: Framework para facilitar la configuración y despliegue.
- **Java 17**: Versión del lenguaje de programación.
- **H2**: Base de datos en memoria para almacenar los items.
- **Swagger UI**: Interfaz de usuario para interactuar fácilmente con la API.
- **Docker**: Se utiliza docker para la contenerizacion de la aplicacion.
- **Docker-compose**: Se utiliza docker-compose para la gestion de los contenedores.
- **Amazon-EC2**: se utiliza un servidor virtual ec2 para el despliegue de la aplicacion.

## Metodología y[README.md](README.md) Principios de Desarrollo
- **Arquitectura Hexagonal**: Utilizada para separar las preocupaciones en capas claramente definidas, facilitando la escalabilidad y mantenimiento.
- **Desarrollo Guiado por el Dominio (DDD)**: Empleando un enfoque centrado en el dominio para mejorar la claridad y la lógica del negocio.
- **Principios SOLID**: Seguidos para promover un diseño de software orientado a objetos limpio y mantenible.

## Estructura de la aplicacion

- **1**: La aplicacion de divide por capas las cuales son.
- **Aplication**: en esta tendremos todos los caso de uso
- **Dominio**: en esta tendremos todos los modelos,utilidades o servicios
- **Infrastrutura**: En esta tendremos los controladores,repositorios,entidades

como vemos en este pequeño diagrama

![Diagrama](diagrama.png)

## Requisitos
- Java 17

## Configuración y Ejecución
### Configuración Local
1. Clonar el repositorio.
2. Ejecutar el proyecto utilizando el comando Gradle:
   ```shell
   ./gradlew bootRun

## Uso de la API
La aplicación expone dos endpoints principales:
1. **GET /api/v1/operation**: Este endpoint realiza el calculo del maximo entero,guarda el resultado en base de datos y entrega el resultado .
   - **Cuerpo de la petición**: JSON con los parametros necesarios para realizar el calculo.
   - **Respuesta**: JSON con el resultado.

2. **GET /api/v1/operation**: Esta api permite ver todos los resultados de los calculados realizados y guardados.
   - **Respuesta**: JSON con el resultado.

La documentación detallada de la API y los esquemas de solicitud y respuesta están disponibles a través de la interfaz de [Swagger](http://44.209.242.173/api/v1/swagger-ui/index.html#/operations-controller/result).

## Pruebas
Las pruebas unitarias se pueden ejecutar mediante el comando:
1. *Ejecuón de pruebas:*
   ```shell
   ./gradlew test