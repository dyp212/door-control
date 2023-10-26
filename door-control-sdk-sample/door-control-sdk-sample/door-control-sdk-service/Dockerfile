FROM --platform=amd64 openjdk:8-jdk-alpine

ENV LC_ALL C.UTF-8
ENV LANG C.UTF-8
ENV TZ asia/Shanghai

COPY target/*.jar app.jar

EXPOSE 6080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Duser.timezone=GMT+08","-Dfile.encoding=utf-8","/app.jar"]