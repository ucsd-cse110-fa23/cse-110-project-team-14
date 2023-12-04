# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-alpine

# Set the working directory
WORKDIR /usr/src/app

# Install required dependencies, including X11
RUN apk --no-cache add libx11 libxext libxrender

# Install Xvfb (X Virtual FrameBuffer) for headless operation
RUN apk --no-cache add xvfb

# Copy the Gradle wrapper files and build.gradle to the container
COPY gradlew .
COPY build.gradle .

# Copy the Gradle wrapper folder to the container
COPY gradle gradle

# Copy the application source code to the container
COPY . .

# Build the application
RUN ./gradlew build

# Expose the default X11 port
EXPOSE 6000
RUN apk --no-cache add libx11 libxext libxrender
ENV DISPLAY=host.docker.internal:0


# Set environment variables for Xvfb
# ENV DISPLAY=:0
ENV SCREEN=0

# Run Xvfb in the background
CMD ["Xvfb", ":0", "-screen", "0", "800x600x16"]

# Run the JavaFX application
CMD ["./gradlew", "run"]
