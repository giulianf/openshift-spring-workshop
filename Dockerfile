FROM openjdk:17-jdk-alpine as build
WORKDIR /workspace/app


COPY gradlew .
COPY gradle gradle
COPY gradle.properties gradle.properties
COPY version.gradle .
COPY settings.gradle .
COPY build.gradle .
RUN ./gradlew dependencies

COPY src src
RUN ./gradlew bootJar -x test
RUN mkdir ./libs
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM openjdk:17-jdk-alpine
RUN apk add --no-cache tzdata
ENV TZ Europe/Brussels
VOLUME /tmp

ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-XX:MaxRAMPercentage=80.0","-cp","app:app/lib/*","com.openshift.openshiftspringworkshop.OpenshiftSpringWorkshopApplication"]




#COPY build/libs/*.jar lawfirm.jar
#ENV LOGGING_FILE_NAME=""
#ENTRYPOINT ["java", "-server", "-XX:MaxRAMPercentage=80.0", "-XshowSettings:vm", "-jar", "/lawfirm.jar"]
