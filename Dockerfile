FROM openjdk:8-jdk-alpine
ADD target/covid19-0.0.1-SNAPSHOT.jar covid19.jar
ENTRYPOINT ["java", "-jar", "covid19.jar"]
EXPOSE 7000
