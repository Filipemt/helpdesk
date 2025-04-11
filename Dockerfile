# Etapa de build: usa imagem oficial com Maven + Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro da imagem
WORKDIR /app

# Copia o pom.xml e o src para o diretório de trabalho
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o .jar (sem rodar testes)
RUN mvn clean package -DskipTests

# Etapa final: imagem leve com só o .jar
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
