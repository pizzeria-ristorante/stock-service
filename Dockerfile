FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jre-alpine
RUN mkdir /opt/app/
COPY --from=build /workspace/app/target/*.jar /opt/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]