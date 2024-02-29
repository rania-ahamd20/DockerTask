FROM openjdk:17-jdk

WORKDIR /app
COPY ./enter-data-service/target/enter-data-service-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "enter-data-service-0.0.1-SNAPSHOT.jar"]
