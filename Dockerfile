FROM maven:3.5.2-jdk-8-alpine

COPY /target/policy-0.0.1-SNAPSHOT.jar myapi.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/myapi.jar"]