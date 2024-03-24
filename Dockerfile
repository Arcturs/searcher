FROM maven:3.8.3-openjdk-17-slim as BUILDER
WORKDIR /opt/build/people
COPY pom.xml /opt/build/people/
COPY src /opt/build/people/src/
RUN mvn -f /opt/build/people/pom.xml clean package -B -DskipTests


FROM openjdk:17-alpine
WORKDIR /opt/app/people
COPY --from=BUILDER /opt/build/people/target/*.jar /opt/app/people/people.jar

RUN apk --no-cache add curl

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java","-jar", "/opt/app/people/people.jar"]