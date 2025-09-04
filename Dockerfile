FROM gradle:8.7.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew && ./gradlew --no-daemon clean build -x test

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
