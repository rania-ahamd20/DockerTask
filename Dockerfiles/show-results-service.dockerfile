FROM openjdk:17-jdk

WORKDIR /app
COPY ./show-results-service/target/show-results-service-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "show-results-service-0.0.1-SNAPSHOT.jar"]
