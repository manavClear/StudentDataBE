FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app.jar"]