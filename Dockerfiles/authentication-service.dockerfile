FROM openjdk:17-jdk

WORKDIR /app
COPY ./authentication-service/target/authentication-service-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "authentication-service-0.0.1-SNAPSHOT.jar"]
