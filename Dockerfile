FROM openjdk:17
WORKDIR /tmp
COPY /target/*.jar test.jar
ENTRYPOINT ["java", "-jar", "test.jar"]
EXPOSE 8084