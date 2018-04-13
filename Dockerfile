FROM frolvlad/alpine-oraclejdk8:slim

LABEL Description="Pool customer api"

ARG VERSION=0.0.1

ADD build/libs/customer-api-${VERSION}-SNAPSHOT.jar app.jar

RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]