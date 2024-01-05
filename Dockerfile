# Etapa de construcción
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

# Etapa de producción
FROM adoptopenjdk:17-jre-hotspot

WORKDIR /app

COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Copia el archivo de configuración específico para el contenedor
COPY src/main/resources/application-container.properties application.properties

# Variables de entorno para la conexión a MySQL
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mynotesdb
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=

# Comando para ejecutar la aplicación cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]
