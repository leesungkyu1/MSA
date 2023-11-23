# 도커 런타임에 사용될 도커 이미지 지정
FROM openjdk:11-slim

# Add Maintainer Info
LABEL maintainer="Illary Huaylupo <illaryhs@gmail.com>"

# dockerfile-maven-plugin에 설정된 JAR_FILE 변수 정의
ARG JAR_FILE

# JAR 파일을 이미지의 파일 시스템에 app.jar로 복사한다.
COPY ${JAR_FILE} app.jar

# unpackage jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

# execute the application
ENTRYPOINT ["java", "-jar", "/app.jar"]