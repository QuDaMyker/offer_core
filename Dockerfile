FROM openjdk:17-jdk-alpine
LABEL authors="Theodore Myker"
ARG JAR_FILE=target/*.jar
COPY ./target/offer_services-0.0.3.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]