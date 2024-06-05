FROM maven:3.6.3-openjdk-17-slim
WORKDIR /app
COPY target/ChatService-0.0.1-SNAPSHOT.jar .
EXPOSE 8086
ENTRYPOINT ["java","-jar","ChatService-0.0.1-SNAPSHOT.jar"]