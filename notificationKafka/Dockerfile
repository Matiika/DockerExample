# Используем официальный образ OpenJDK
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Устанавливаем необходимые пакеты для отладки (опционально)
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Копируем файлы сборки Maven/Gradle
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn

# Загружаем зависимости (для кэширования слоев)
RUN ./mvnw dependency:go-offline -B

# Копируем исходный код
COPY src ./src

# Собираем приложение
RUN ./mvnw clean package -DskipTests

# Создаем финальный образ
FROM openjdk:21-jdk-slim

WORKDIR /app

# Устанавливаем curl для health check
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Копируем собранный JAR файл
COPY --from=0 /app/target/*.jar app.jar

# Создаем пользователя для безопасности
RUN groupadd -r appuser && useradd -r -g appuser appuser
RUN chown -R appuser:appuser /app
USER appuser

# Открываем порт
EXPOSE 8000

# Переменные окружения по умолчанию
ENV SPRING_PROFILES_ACTIVE=docker
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8000/actuator/health || exit 1

# Запускаем приложение
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]