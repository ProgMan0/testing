FROM openjdk:17
WORKDIR /tmp
COPY /target/tserver-0.0.1-SNAPSHOT.jar /tmp/app.jar
ENTRYPOINT ["java", "-jar", "/tmp/app.jar"]
EXPOSE 8084