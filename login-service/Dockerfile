
# Etapa de Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia todos os arquivos do projeto para o container
COPY . .

# Concede permissão de execução ao Maven Wrapper
RUN chmod +x mvnw

# Limpa o cache local e executa o build
RUN ./mvnw clean dependency:purge-local-repository -DskipTests

# Constrói o JAR do projeto
RUN ./mvnw clean package -DskipTests

# Etapa de Execução
FROM eclipse-temurin:17
WORKDIR /app

# Copiar o JAR gerado para o container final
COPY --from=build /app/target/login-service.jar app.jar

# Expor a porta do serviço
EXPOSE 8080

# Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
