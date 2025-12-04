FROM eclipse-temurin:17-jre-alpine
RUN mvn -B clean package -DskipTests
WORKDIR /app
COPY target/hn-innov-api-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]