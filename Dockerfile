# Step 1: Build stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Runtime stage
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/healthcare-0.0.1-SNAPSHOT.jar /app/healthcare.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Xmx512m", "healthcare.jar"]
