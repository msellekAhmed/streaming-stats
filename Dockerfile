FROM openjdk:8
ADD target/stats-1.0.0.jar stats-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "stats-1.0.0.jar"]