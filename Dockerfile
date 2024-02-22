FROM openjdk:17-jdk-alpine

LABEL authors="amt-richard-bouaro"

FROM maven:3.8.6-eclipse-temurin-11-alpine AS builder

RUN mkdir -p /app

WORKDIR /app

RUN addgroup -S app && adduser -S app -S app

USER app

#Copy the pom.xml file
COPY pom.xml .

#Copy the project source
COPY src src

RUN mvn --version

RUN mvn clean install -e

FROM openjdk:7-jre-alpine

WORKDIR /app

COPY --from=builder target/*.jar app.jar

EXPOSE 8080

#COPY /target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]