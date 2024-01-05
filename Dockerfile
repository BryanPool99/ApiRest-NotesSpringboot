# Etapa de construcción
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

# Etapa de producción
FROM maven:3.8.4-openjdk-17-slim

WORKDIR /app

COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]
