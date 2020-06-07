FROM openjdk:8-jdk-alpine
ADD target/covid-19-0.0.1-SNAPSHOT.jar covid-19.jar
ENTRYPOINT ["java", "-jar", "covid-19.jar"]
EXPOSE 7000
