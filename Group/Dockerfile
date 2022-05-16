FROM library/postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB Group

FROM openjdk:18-alpine
MAINTAINER onuryaldir


COPY Group/target/Group-0.0.1-SNAPSHOT.jar /Group-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Group-0.0.1-SNAPSHOT.jar"]



