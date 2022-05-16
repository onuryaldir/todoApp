FROM library/postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB Task

FROM openjdk:18-alpine
MAINTAINER onuryaldir


COPY Task/target/Task-0.0.1-SNAPSHOT.jar /Task-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Task-0.0.1-SNAPSHOT.jar"]

#ENTRYPOINT ["java","-jar","/ApiController-0.0.1-SNAPSHOT.jar"]



