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

- [How to build & run project](#how-to-build--run-project-)
- [Development Stack](#development-stack)
- [Test Stack](#test-stack)
- [Monitoring & Metrics Tools](#monitoring--metrics-stack)
- [Project Structure](#project-structure)
- [Dependency Tree](#dependency-tree)  
- [API samples](#api-samples)
- [Testing](#testing)
- [Tools Enablement](#tools-enablement)
- [References](#references)

## How to build & run project ?

* Clone the git repo - https://github.com/delhibabu86/feature-romannumeral.git
* cd feature-romannumeral
* Execute mvn clean install on the project root folder.Ensure its completed successfully.
* Execute docker
* Make sure all the docker containers have started successfully
* Validate if all docker services are up & running

## Run with docker

*Start docker compose : docker-compose up --build --force-recreate
*Ensure all services are up : docker-compose ps

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
* SpringBootTest

## Monitoring & Metrics Stack

* Actuator
* Prometheus
* Datadog

## Project Structure

![](assets/ProjectStructure.png)

## Dependency Tree
![](assets/dependencytree_1.png)
![](assets/dependencytree_image2.png)


## API samples

## Testing

## Tools Enablement/Setup

## References






