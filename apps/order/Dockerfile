FROM gradle:9.2.1-jdk21 AS build
WORKDIR /app
COPY build.gradle settings.gradle /app/
COPY gradlew /app/
COPY gradle /app/gradle

COPY src /app/src

RUN ./gradlew clean build --no-daemon -x test

FROM eclipse-temurin:21-jre-noble
WORKDIR /app
ARG JAR_FILE=*.jar
COPY --from=build /app/build/libs/$JAR_FILE app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]