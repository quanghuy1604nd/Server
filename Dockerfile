FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./dist /app
CMD ["java", "-jar", "Server.jar"]