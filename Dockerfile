# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Compile the Java program
RUN javac DinoGame.java

# Expose the new port
EXPOSE 8081

# Run the application on the new port
CMD ["java", "DinoGame"]
