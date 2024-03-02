# Utilizar una imagen base con Java 11
FROM openjdk:17-jdk-slim

# Copiar el JAR compilado en el contenedor
COPY calculateMaxIntegerInclusionCloud-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","/app.jar"]
