#
# Build stage
#
FROM maven:3.6.3-jdk-11-slim AS maven-build
COPY src /src
COPY pom.xml .
RUN mvn -f /pom.xml clean install -Dmaven.test.skip

#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY --from=maven-build /target/employee-application.jar app.jar
# ADD target/employee-application.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","app.jar" ]


# docker build -t at-employee .
# docker run -p 9090:8080 at-employee