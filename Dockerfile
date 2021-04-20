#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/risk-engine-0.0.1-SNAPSHOT.jar /usr/local/lib/risk-engine-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/risk-engine-1.0.0.jar"]

#FROM adoptopenjdk:11-jre-hotspot

#COPY target/risk-engine-0.0.1-SNAPSHOT.jar risk-engine-1.0.0.jar

#ENTRYPOINT ["java","-jar","/risk-engine-1.0.0.jar"]