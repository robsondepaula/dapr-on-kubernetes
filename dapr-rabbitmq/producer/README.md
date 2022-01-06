1. Build:
```
./gradlew clean build
```
2. Run:
```
dapr run --app-id dapr-producer --app-port 8080 --dapr-http-port 9091 --components-path ./dapr-components -- java -jar build/libs/producer-0.0.1-SNAPSHOT.jar
```
3. Validate:
```
curl -X POST http://localhost:9091/v1.0/invoke/dapr-producer/method/produce \
   -H "Content-Type: application/json" \
   -d '{"productId": 123456, "quantity": 100}'
```