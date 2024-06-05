FROM maven:3.6.3-openjdk-17-slim
WORKDIR /app
COPY target/mc-0.0.1-SNAPSHOT.jar .
EXPOSE 8083
ENTRYPOINT ["java","-jar","mc-0.0.1-SNAPSHOT.jar"]

