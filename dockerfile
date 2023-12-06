# Use an official OpenJDK runtime as a base image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file into the container at /app
COPY server.jar /app

# Make port 8100 available to the world outside this container
EXPOSE 8100

# Run the JAR file
CMD ["java", "-jar", "server.jar"]