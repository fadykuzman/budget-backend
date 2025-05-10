# Build stage
FROM gradle:8.5-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -x test

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
RUN echo "192.168.178.69 codefuchs.dev" >> /etc/hosts
# Create a non-root user to run the application
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
