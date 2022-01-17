FROM gradle:jdk11-alpine as build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN ./gradlew clean build -DskipTests

FROM adoptopenjdk/openjdk11:jre-11.0.13_8-alpine
RUN apk add dumb-init
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build /project/build/libs/eureka-server-0.0.1-SNAPSHOT.jar /app/eureka-server-0.0.1-SNAPSHOT.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
CMD "dumb-init" "java" "-jar" "eureka-server-0.0.1-SNAPSHOT.jar"