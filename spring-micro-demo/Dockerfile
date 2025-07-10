# Multi-stage Dockerfile для сборки всех сервисов
FROM eclipse-temurin:21-jdk AS builder

# Установка Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Создание рабочей директории
WORKDIR /app

# Копирование корневого pom.xml и модулей
COPY pom.xml .
COPY eureka-server/ eureka-server/
COPY eureka-client/ eureka-client/
COPY config-server/ config-server/
COPY api-gateway/ api-gateway/

# Сборка всех модулей с Spring Boot repackage
RUN mvn clean package spring-boot:repackage -DskipTests

# Создание отдельных образов для каждого сервиса
FROM eclipse-temurin:21-jre AS eureka-server
WORKDIR /app
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
COPY --from=builder /app/eureka-server/target/*-SNAPSHOT.jar app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jre AS eureka-client
WORKDIR /app
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
COPY --from=builder /app/eureka-client/target/*-SNAPSHOT.jar app.jar
EXPOSE 8083
CMD ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jre AS config-server
WORKDIR /app
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
COPY --from=builder /app/config-server/target/*-SNAPSHOT.jar app.jar
EXPOSE 8888
CMD ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jre AS api-gateway
WORKDIR /app
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
COPY --from=builder /app/api-gateway/target/*-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]