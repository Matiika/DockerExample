# Этап сборки
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Копируем файлы для сборки
COPY pom.xml .
COPY src ./src

# Устанавливаем Maven и собираем проект
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*
RUN mvn clean package -DskipTests

# Этап выполнения
FROM eclipse-temurin:21-jre

WORKDIR /app

# Копируем JAR файл из этапа сборки
COPY --from=builder /app/target/*.jar app.jar

# Открываем порт приложения
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java", "-jar", "app.jar"]