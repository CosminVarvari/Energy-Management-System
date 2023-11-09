FROM maven:3.6.3-openjdk-17-slim
WORKDIR /app
COPY target/deviceservice-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
ENTRYPOINT ["java","-jar","deviceservice-0.0.1-SNAPSHOT.jar"]

