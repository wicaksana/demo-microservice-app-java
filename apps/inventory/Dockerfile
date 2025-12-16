FROM gradle:9.2.1-jdk25 AS build
WORKDIR /app
COPY build.gradle settings.gradle /app/
COPY gradlew /app/
COPY gradle /app/gradle

COPY src /app/src

RUN ./gradlew clean build --no-daemon -x test

FROM eclipse-temurin:25-jre-noble
WORKDIR /app
ARG JAR_FILE=*.jar
COPY --from=build /app/build/libs/$JAR_FILE app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]