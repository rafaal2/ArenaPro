# ===================== Etapa de Build =====================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Instala o dos2unix (para converter EOL se necessário)
RUN apt-get update && apt-get install -y dos2unix

# Copia o Maven Wrapper (se quiser usar dentro do Docker)
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd


# (Opcional) Converter CRLF → LF e remover BOM, se houver
RUN dos2unix -f mvnw
RUN dos2unix -f mvnw.cmd || true
RUN dos2unix -f .mvn/wrapper/maven-wrapper.properties || true
RUN dos2unix -f .mvn/wrapper/MavenWrapperDownloader.java || true

# Copia o restante do projeto
COPY pom.xml .
COPY src ./src

# Compila o projeto
RUN mvn clean install -DskipTests

# ===================== Etapa de Execução =====================
FROM eclipse-temurin:17
WORKDIR /app

# Copiar o JAR gerado na etapa anterior
COPY --from=build /app/target/arenapro-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta e rodar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
