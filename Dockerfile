FROM openjdk:17-jdk-alpine
COPY build/libs/kafka-producer-0.0.1-SNAPSHOT.jar kafka-producer.jar

EXPOSE 9393

# Command to run when the container starts
CMD ["java", "-jar", "kafka-producer.jar"]