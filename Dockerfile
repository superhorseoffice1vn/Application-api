# Use a base image with OpenJDK 11 for Spring Boot
FROM gradle:8.5.0-jdk11 AS build
WORKDIR /app
COPY ./ /app
RUN ./gradlew build -x test

# Use another lightweight base image for the JRE
FROM adoptopenjdk:11-jre-hotspot

# Copy Spring Boot JAR to app folder
COPY --from=build /app/build/libs/be-app.jar /app/be-app.jar

# Expose port
EXPOSE 8080

# Command to start Spring Boot
CMD ["java", "-jar", "/app/be-app.jar"]

