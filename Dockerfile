FROM eclipse-temurin:19
WORKDIR /app
COPY target/*.jar app.jar
COPY target/onlinestore-0.0.1-SNAPSHOT.jar /app/onlinestore.jar
ENTRYPOINT ["java", "-jar", "onlinestore.jar"]