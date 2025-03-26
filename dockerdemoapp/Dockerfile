FROM openjdk:21-slim-buster
MAINTAINER Sayantan
#Copy our jar file to container
COPY target/dockerdemoapp-0.0.1-SNAPSHOT.jar app.jar
#run our application
ENTRYPOINT ["java","-jar","app.jar"]


