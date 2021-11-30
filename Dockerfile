FROM amazoncorretto:11 as builder
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} school-service.jar
ENTRYPOINT ["java","-jar","/school-service.jar"]
