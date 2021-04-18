FROM adoptopenjdk:11-jre-hotspot

COPY target/risk-engine-0.0.1-SNAPSHOT.jar risk-engine-1.0.0.jar

ENTRYPOINT ["java","-jar","/risk-engine-1.0.0.jar"]