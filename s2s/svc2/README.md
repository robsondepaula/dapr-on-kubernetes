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

# Container build
## Dockerfile
Multilayered build:
```
docker build . -t registry.localhost:5000/svc2
```
Push to local registry:
```
docker push registry.localhost:5000/svc2
```

# Deploy to k8s
If you do not have a local k8s cluster, check this [suggestion](../README.md).

Create the ConfigMap:
```
kubectl apply -f manifests/configmap.yaml
```
Check it is available:
```
kubectl get configmap svc2-config
```
Deploy svc2 itself:
```
kubectl apply -f manifests/deployment.yaml
```
Wait for svc2 pod to reach Running status:
```
kubectl get pods -w
```
If the pod status is not Running check the logs, like so:
```
kubectl logs svc2-dep-68667b98cf-qxt6z
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
svc2-svc     NodePort    10.43.179.254   <none>        2347:30081/TCP   8s
```
And finally:
```
curl localhost:30081/remote-hello
```