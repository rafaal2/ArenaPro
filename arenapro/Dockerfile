# ===================== Etapa de Build =====================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Instala o dos2unix
RUN apt-get update && apt-get install -y dos2unix

# Copia os arquivos do Maven Wrapper
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd
COPY .mvn .mvn

# (Opcional) Converter os arquivos do wrapper para remover CRLF e BOM
RUN dos2unix -f mvnw
RUN dos2unix -f mvnw.cmd || true
RUN dos2unix -f .mvn/wrapper/maven-wrapper.properties || true
RUN dos2unix -f .mvn/wrapper/MavenWrapperDownloader.java || true
# ATENÇÃO: não rode dos2unix no maven-wrapper.jar (binário)

# Copia o restante do projeto
COPY pom.xml .
COPY src ./src

# Agora que tudo está convertido, podemos compilar
RUN mvn clean install -DskipTests
# ...

# ====================== Etapa de Execução ======================
FROM eclipse-temurin:17
WORKDIR /app

# (7) Copiar o JAR gerado na etapa anterior
COPY --from=build /app/target/arenapro-0.0.1-SNAPSHOT.jar app.jar

# (8) Expor a porta do serviço
EXPOSE 8080

# (9) Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]