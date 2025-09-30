FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the Spring Boot JAR built by your pipeline
COPY target/*-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
