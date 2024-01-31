FROM openjdk:12-alpine

LABEL authors="amt-richard-bouaro"

RUN addgroup -S app && adduser -S app -S app

USER app

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]