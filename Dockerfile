FROM openjdk:15-jdk-alpine
ARG APP_JAR_LOCATION=target/romannumeral*.jar
COPY ${APP_JAR_LOCATION} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]