FROM openjdk:19-jdk-alpine
WORKDIR /app
COPY target/onlinestore-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
