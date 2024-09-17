FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY ./dist /app
CMD ["java", "-jar", "Server.jar"]