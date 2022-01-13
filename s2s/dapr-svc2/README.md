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

## Zipkin
Each sidecar populates the self-hosted Zipkin by default. Check the tracing by visiting http://localhost:9411/zipkin.

# Container build
## Dockerfile
Multilayered build:
```
docker build . -t registry.localhost:5000/dapr-svc2
```
Push to local registry:
```
docker push registry.localhost:5000/dapr-svc2
```

# Deploy to k8s (with dapr sidecar)

Check the necessary [annotations](https://docs.dapr.io/operations/hosting/kubernetes/kubernetes-overview/) to make this [deployment](./manifests/deployment.yaml) "dapr enabled".

Proceed with the deployment by:
```
kubectl apply -f manifests/deployment.yaml
```
Wait for dapr-svc2 pod to reach Running status:
```
kubectl get pods -w
```
If the pod status is not Running check the app container logs, like so:
```
kubectl logs dapr-svc2-dep-66d69b779-w99xg dapr-svc2-container
```
And perhaps the dapr sidecar logs might give good hints too:
```
kubectl logs dapr-svc2-dep-66d69b779-w99xg daprd
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
dapr-svc2-dapr   ClusterIP   None            <none>        80/TCP,50001/TCP,50002/TCP,9090/TCP   5m1s
dapr-svc2-svc    NodePort    10.43.99.150    <none>        2348:30082/TCP                        16s
```
And finally:
```
curl localhost:30082/remote-hello
```