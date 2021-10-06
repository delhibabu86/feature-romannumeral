# romannumeral
MicroService Architecture - Spring Boot Application exposing GET API.Converts integer into roman numeral.

API Contract & Design : http://localhost:8080/swagger-ui.html

##Pre-requisites

* Install Java 15 - https://jdk.java.net/archive/
* Install maven - https://maven.apache.org/download.cgi
* Install docker - https://docs.docker.com/get-docker/
* Execute java -version on terminal & make sure jdk15 is the version
* Execute mvn --version on terminal
* Install docker and ensure its up & running on the machine

## Summary

- [How to build & run project ?](#build-run-project)
- [Development Stack](#engineering-stack)
- [Test Stack](#test-stack)
- [Monitoring & Metrics Stack](#monitoring-metrics-stack)
- [Project Structure](#project-structure)
- [API samples](#api-samples)
- [Testing](#testing)
- [Tools Enablement](#enabling-tools)
- [References](#references)

## How to build & run project ?

* Clone the git repo - https://github.com/delhibabu86/feature-romannumeral.git
* cd feature-romannumeral
* Execute mvn clean install on the project root folder.Ensure its completed successfully.
* Execute docker
* Make sure all the docker containers have started successfully
* Validate if all docker services are up & running

## Run Locally

* execute docker stop
* Choose either step 3 or step4
* Option 1 - Start the application from IntelliJ/Eclipse
* Option 2 -  
              a)  To run from terminal, do mvn clean install first.
              b) java -jar target/roman-numeral-0.0.1-SNAPSHOT.jar
* Hit swagger end point & confirm services are up & running.

## Run with docker

docker build -t roman-numeral:0.0.1 .

docker run --net=host

## Development Stack

* Java 15
* spring-boot 2.5.5
* Maven 3.8.2
* Swagger 
* Docker
* Docker Compose

## Test Stack

* Junit5
* Mockito

## Monitoring & Metrics Stack

* spring actuator
* datadog

## Project Structure

## API samples

## Testing

## Tools Enablement

## References






