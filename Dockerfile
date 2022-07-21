FROM openjdk:11

WORKDIR /service
COPY target/person-0.0.1-SNAPSHOT.jar /service/app.jar
CMD ["java", "-jar", "app.jar"]