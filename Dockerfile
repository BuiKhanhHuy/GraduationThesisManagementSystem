FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build target/GraduationThesisManagementSystem.war ./app.war
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
