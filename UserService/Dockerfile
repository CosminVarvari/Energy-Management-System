FROM maven:3.6.3-openjdk-17-slim
WORKDIR /app
COPY target/userservice-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","userservice-0.0.1-SNAPSHOT.jar"]

