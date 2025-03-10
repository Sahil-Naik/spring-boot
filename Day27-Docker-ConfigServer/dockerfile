# Use an OpenJDK base image
FROM openjdk:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the built JAR file
COPY target/Day27-Docker-ConfigServer-3.4.3.jar config-server.jar

# Expose the Eureka Server port
EXPOSE 8888

# Run the application
ENTRYPOINT ["java", "-jar", "config-server.jar"]
