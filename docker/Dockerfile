# Используем образ с Java для сборки
FROM openjdk:17
# Устанавливаем рабочую директорию
ARG APP_JAR=*.jar

WORKDIR /app

# Копируем JAR-файл в рабочую директорию
COPY ${APP_JAR} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080

