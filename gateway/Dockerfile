FROM maven:3.9.9-amazoncorretto-23-debian AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean package install

FROM eclipse-temurin:21-jre-alpine

RUN apk add --no-cache curl
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build app/target/*.jar /opt/app/application.jar


CMD ["java", "-jar", "/opt/app/application.jar"]