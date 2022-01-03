# Dependency
## Local validation
Requires [svc1](../svc1/README.md) up and running on DAPR (self-hosted).

# DAPR
Build:
```
./gradlew clean build
```
Run:
```
dapr run --app-id dapr-svc2 --app-port 8081 --dapr-http-port 9091 -- java -jar build/libs/dapr-svc2-0.0.1-SNAPSHOT.jar
```
Validate:
```
curl localhost:9091/v1.0/invoke/dapr-svc2/method/remote-hello | jq
```
This will request the side-car of dapr-svc2 to communicate with the side-car in sv1 and complete the remote service invocation.