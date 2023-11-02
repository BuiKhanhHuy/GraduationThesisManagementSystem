FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM tomcat:10-jdk11
COPY --from=build /app/target/GraduationThesisManagementSystem.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]
