# Etapa 1: Construcción de la aplicación
FROM maven:3.9.2-eclipse-temurin-17-alpine AS build

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copia el resto del código de la aplicación
COPY src ./src

# Compila la aplicación y empaca el JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final de ejecución
FROM eclipse-temurin:17-alpine

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el JAR desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto que usará la aplicación
EXPOSE 8080

# Define el punto de entrada del contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]
