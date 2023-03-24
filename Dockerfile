FROM openjdk:11

LABEL maintainer="https://github.com/dasalgadoc"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=./target/cqrs-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} cqrs-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cqrs-0.0.1-SNAPSHOT.jar"]
