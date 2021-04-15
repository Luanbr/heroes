FROM openjdk:16
COPY ./target/heroes-0.0.1.jar /app.jar
WORKDIR /
CMD ["java", "-jar", "app.jar"]