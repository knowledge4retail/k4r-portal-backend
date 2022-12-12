FROM maven:3.8-openjdk-17-slim as MAVEN_TOOL_CHAIN

WORKDIR /build

COPY ./src/ /build/src/
COPY ./pom.xml /build/pom.xml

RUN mvn --batch-mode --update-snapshots \
      -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=WARN \
      -Dorg.slf4j.simpleLogger.showDateTime=true -Dorg.slf4j.simpleLogger.dateTimeFormat=HH:mm:ss,SSS \
      clean install

FROM openjdk:17-alpine
COPY --from=MAVEN_TOOL_CHAIN /build/target/portalbackend.jar /usr/app/app.jar

ENTRYPOINT ["java","-jar","/usr/app/app.jar"]
