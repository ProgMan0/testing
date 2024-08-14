FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8084
WORKDIR /bot
COPY /target/tserver-0.0.1-SNAPSHOT.jar test.jar
ENTRYPOINT ["java", "-jar", "test.jar"]