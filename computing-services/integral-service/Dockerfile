FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/integral-service-1.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar 
 
