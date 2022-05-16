FROM library/postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB User

FROM openjdk:18-alpine
MAINTAINER onuryaldir


COPY User/target/User-0.0.1-SNAPSHOT.jar /User-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/User-0.0.1-SNAPSHOT.jar"]
#


