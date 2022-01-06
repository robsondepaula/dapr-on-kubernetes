# Build
## Jar
```
./gradlew clean build
```
Validate:
```
java -jar build/libs/svc1-0.0.1-SNAPSHOT.jar
```
```
curl localhost:8080/hello | jq
```
### DAPR
```
dapr run --app-id dapr-svc1 --app-port 8080 --dapr-http-port 9090 -- java -jar build/libs/svc1-0.0.1-SNAPSHOT.jar
```
```
curl localhost:9090/v1.0/invoke/dapr-svc1/method/hello | jq
```
## Docker image with tag on the local registry
```
./gradlew clean bootBuildImage --imageName=registry.localhost:5000/svc1
```
## Push to local registry
```
docker push registry.localhost:5000/svc1
```
Verify the push was successful:
```
curl -s registry.localhost:5000/v2/_catalog | jq
```
## Validate
```
docker run -d -p 8080:8080 --name svc1 registry.localhost:5000/svc1
```
```
curl localhost:8080/hello
```
Stop the running container:
```
docker ps | grep registry.localhost:5000/svc1 | awk '{print $1}' | xargs docker stop
```
# Deploy to k8s
```
kubectl apply -f manifests/deployment.yaml
```
Wait for svc1 pod to reach Running status:
```
kubectl get pods -w
```
Deploy the NodePort service:
```
kubectl apply -f manifests/service.yaml
```
## Validate
```
kubectl get service
```
Example:
```
svc1-svc     NodePort    10.43.179.254   <none>        2346:30080/TCP   8s
```
And finally:
```
curl localhost:8082/hello
```
For a refresher about exposing services on k3d, check  https://k3d.io/v5.2.2/usage/exposing_services/.