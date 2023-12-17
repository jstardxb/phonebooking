FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
LABEL authors="joaquim"

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml package -DskipTests

FROM eclipse-temurin:21.0.1_12-jre-ubi9-minimal
COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]