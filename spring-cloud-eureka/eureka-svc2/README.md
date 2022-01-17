# Dependency
## Local validation
Requires [eureka-svc1](../eureka-svc1/README.md) up.

```
curl localhost:8081/remote-hello | jq
```

# Container build
## Dockerfile
Multilayered build:
```
docker build . -t registry.localhost:5000/eureka-svc2
```
Push to local registry:
```
docker push registry.localhost:5000/eureka-svc2
```

# Deploy to k8s

Proceed with the creation of a deployment by:
```
kubectl apply -f manifests/deployment.yaml
```
Wait for eureka-svc2 pod to reach Running status:
```
kubectl get pods -w
```
If the pod status is not Running check the container logs, like so:
```
kubectl logs eureka-svc2-dep-7789f5ff97-jlrcc
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
eureka-svc2-svc         NodePort    10.43.166.245   <none>        2350:30002/TCP                        11s
```
And finally:
```
curl localhost:30002/remote-hello
```