FROM openjdk:17-jdk-slim-buster
RUN set -x \
    mkdir -p /app
COPY ./build/libs/inbox-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
WORKDIR /app
CMD ["java", "-jar", "inbox-0.0.1-SNAPSHOT.jar"]
