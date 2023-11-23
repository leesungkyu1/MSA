# stage 1
FROM openjdk:11-slim as build

# Add Maintainer Info
LABEL maintainer="Illary Huaylupo <illaryhs@gmail.com>"

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

# 앞서 빌드 이미지의 파일 시스템에 복사한 app.jar의 압축을 푼다
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

# stage 2
# 새로운 이미지는 스프링 부트 앱에 대한 통짜 JAR 파일 대신 여러 레이어로 구성 됨
FROM openjdk:11-slim

#Add volume pointing to /tmp
VOLUME /tmp

# Copy unpackaged application to new container
# 빌드라고 명명된 첫 이미지에서 여러 레이어를 복사한다.
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

#컨테이너가 생성될 때 이미지의 라이선싱 서비스를 실행 대상으로 지정한다.
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.optimagrowth.license.LicneseServiceApplication"]
