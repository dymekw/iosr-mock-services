FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/function-value-service-1.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar 
