# Use an official OpenJDK runtime as a parent image
FROM openjdk:23-ea-17-jdk-bullseye

# Set the working directory inside the container
WORKDIR /app

# Copy the application jar file into the container
COPY user-manager/target/user-manager-0.0.1-SNAPSHOT.jar /app/user-management.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "user-management.jar"]
