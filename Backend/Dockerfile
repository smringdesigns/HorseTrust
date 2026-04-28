# HorseTrust/Backend/horsemarket/Dockerfile

# Dockerfile - Versión simple (SIN etapa build)
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiamos el JAR directamente (ya compilado localmente)
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]