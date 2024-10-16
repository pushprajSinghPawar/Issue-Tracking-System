# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY /target/UserMicroservice-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (default port for Spring Boot applications)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
