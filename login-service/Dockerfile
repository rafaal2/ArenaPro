# ===================== Etapa de Build =====================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

RUN apt-get update && apt-get install -y dos2unix

COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd

RUN dos2unix -f mvnw
RUN dos2unix -f mvnw.cmd || true
RUN dos2unix -f .mvn/wrapper/maven-wrapper.properties || true
RUN dos2unix -f .mvn/wrapper/MavenWrapperDownloader.java || true

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

# ===================== Etapa de Execução =====================
FROM eclipse-temurin:17
WORKDIR /app

COPY --from=build /app/target/login-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
