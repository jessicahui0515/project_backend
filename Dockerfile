
FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ./build/libs/FSSE2510_project-2.0.3.jar Project_Backend.jar
ENTRYPOINT ["java","-jar","/Project_Backend.jar"]
