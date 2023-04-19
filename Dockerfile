FROM eclipse-temurin:17-jdk-jammy
COPY target/SpringRest.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
