# Build stage
# FIX: Upgraded to use Temurin 21 Maven image to support Java 21 compilation
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Package stage
# FIX: Upgraded to use Temurin 21 JDK image for runtime
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# NOTE: Please verify that the JAR file name 'facebookapi-0.0.1-SNAPSHOT.jar' 
# matches the actual artifact name created by your Maven build.
COPY --from=build /app/target/facebookapi-0001-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]