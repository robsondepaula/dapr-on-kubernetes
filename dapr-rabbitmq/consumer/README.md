1. Build:
```
./gradlew clean build
```
2. Run:
```
dapr run --app-id dapr-consumer --app-port 8081 --dapr-http-port 9092 --components-path ./dapr-components -- java -jar build/libs/consumer-0.0.1-SNAPSHOT.jar
```
3. Validate:
Inspect logs after validating the producer.