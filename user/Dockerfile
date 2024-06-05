FROM maven:3.6.3-openjdk-17-slim
WORKDIR /app
COPY target/UserMicroservice-1.0-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","UserMicroservice-1.0-SNAPSHOT.jar"]
