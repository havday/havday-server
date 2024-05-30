FROM eclipse-temurin:21-jdk-alpine as builder

WORKDIR /workspace/app
COPY .. /workspace/app
ENTRYPOINT /workspace/app/gradlew :product-service:build

FROM eclipse-temurin:21-jre-alpine

COPY --from=builder /workspace/app/build/libs/*.jar ./app.jar

EXPOSE 8080
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]