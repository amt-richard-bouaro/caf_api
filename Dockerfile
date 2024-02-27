## Use an official Maven image as the base image
#FROM maven:3.8.4-openjdk-17-slim AS build
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the pom.xml and the project files to the container
#COPY pom.xml .
#COPY src ./src
#
## Build the application using Maven
#RUN mvn clean package -DskipTests
#
## Use an official OpenJDK image as the base image
#FROM openjdk:11-jre-slim
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the built JAR file from the previous stage to the container
#COPY --from=build /target/*.jar app.jar
#
## Set the command to run the application
#ENTRYPOINT ["java", "-jar", "app.jar"]

# Stage 1: Build the application
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Stage 2: Create the Docker image
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=build /workspace/target/*.jar /app/caf-api.jar
ENTRYPOINT ["java", "-jar","/app/caf-api.jar"]