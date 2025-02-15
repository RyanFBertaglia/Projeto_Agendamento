FROM maven:3.9.9-eclipse-temurin-17-alpine AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install -DskipTests

FROM openjdk:17-alpine

COPY --from=build /app/target/demo-0.1.0-SNAPSHOT.war /app/app.war
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "app.war"]
