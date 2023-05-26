FROM eclipse-temurin:11.0.18_10-jdk-alpine
COPY /build/libs/bookin-0.0.1-SNAPSHOT.jar bookin-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/bookin-0.0.1-SNAPSHOT.jar"]