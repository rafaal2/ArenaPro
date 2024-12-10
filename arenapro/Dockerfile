# Etapa de Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar o código-fonte e o Maven Wrapper para o container
COPY . .

# Construir o JAR usando o Maven Wrapper
RUN ./mvnw clean package -DskipTests

# Etapa de Execução
FROM eclipse-temurin:17
WORKDIR /app

# Copiar o JAR gerado para o container final
COPY --from=build /app/target/arenapro.jar app.jar

# Expor a porta do serviço
EXPOSE 8081

# Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
