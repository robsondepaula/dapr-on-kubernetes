# Dependency
## Local validation
Requires [svc1](../svc1/README.md) up and running on port 8080.

# Build
## Jar
```
./gradlew clean build
```
Validate:
```
java -jar build/libs/svc2-0.0.1-SNAPSHOT.jar
```
```
curl localhost:8081/remote-hello | jq
```
