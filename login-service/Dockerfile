# Etapa de Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o Maven Wrapper e o diretório .mvn
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn

# Copia o pom.xml
COPY pom.xml .

# Copia o diretório src
COPY src ./src

# Concede permissão de execução ao Maven Wrapper
RUN chmod +x mvnw

# Executa o build sem testes
RUN ./mvnw clean install -DskipTests

# Etapa de Execução
FROM eclipse-temurin:17
WORKDIR /app

# Copiar o JAR gerado para o container final
COPY --from=build /app/target/login-service-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta do serviço
EXPOSE 8080

# Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]