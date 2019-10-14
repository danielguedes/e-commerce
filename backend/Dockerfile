FROM gradle:jdk8 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew assemble
CMD [ "ls /home/gradle/src/ "]

FROM openjdk:8-jdk-alpine
LABEL maintainer="daniel.guedes@gmail.com"
VOLUME /tmp
EXPOSE 8080
EXPOSE 8080
COPY --from=builder /home/gradle/src/build/libs/ECommerce-0.1.0.jar .
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ECommerce-0.1.0.jar"]