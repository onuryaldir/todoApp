FROM openjdk:18-alpine
MAINTAINER onuryaldir

COPY ApiController/target/ApiController-0.0.1-SNAPSHOT.jar /ApiController-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ApiController-0.0.1-SNAPSHOT.jar"]

COPY Group/target/Group-0.0.1-SNAPSHOT.jar /Group-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/User-0.0.1-SNAPSHOT.jar"]
#
COPY User/target/User-0.0.1-SNAPSHOT.jar /User-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/User-0.0.1-SNAPSHOT.jar"]
#
COPY Task/target/Task-0.0.1-SNAPSHOT.jar /Task-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/Task-0.0.1-SNAPSHOT.jar"]

#ENTRYPOINT ["java","-jar","/ApiController-0.0.1-SNAPSHOT.jar"]



