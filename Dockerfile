FROM openjdk:11-jdk-slim
EXPOSE 8080

ADD target/employee-application.jar employee-application.jar
ENTRYPOINT [ "java","-jar","employee-application.jar" ]


# docker build -t at-employee .
# docker run -p 9090:8080 at-employee