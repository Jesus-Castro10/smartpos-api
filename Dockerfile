# Etapa de construcción: compilar la app con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos todo el proyecto
COPY pom.xml .
COPY src ./src

# Compilamos la app (sin tests)
RUN mvn clean package -DskipTests

# Etapa de ejecución: usar JDK liviano solo con el jar
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiamos el jar generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto expuesto (Render espera 8080)
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]