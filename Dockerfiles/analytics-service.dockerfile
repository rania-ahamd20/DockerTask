FROM openjdk:17-jdk

WORKDIR /app
COPY ./analytics-service/target/analytics-service-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "analytics-service-0.0.1-SNAPSHOT.jar"]
