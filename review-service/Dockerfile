FROM eclipse-temurin:21-jre-alpine

COPY /build/libs/*.jar ./app.jar

EXPOSE 8080
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]