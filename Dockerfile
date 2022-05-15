FROM openjdk:18-alpine
MAINTAINER onuryaldir
COPY target/docker-apiGateway-server-1.0.0.jar message-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/message-server-1.0.0.jar"]