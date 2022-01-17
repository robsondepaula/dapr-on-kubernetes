# Dependency
## Local validation
Requires [eureka-server](../eureka-server/README.md) up and running.

To validate, visit [eureka-svc2](../eureka-svc2/README.md)

# Container build
## Dockerfile
Multilayered build:
```
docker build . -t registry.localhost:5000/eureka-svc1
```
Push to local registry:
```
docker push registry.localhost:5000/eureka-svc1
```

# Deploy to k8s

Proceed with the creation of a deployment by:
```
kubectl apply -f manifests/deployment.yaml
```
Wait for eureka-svc1 pod to reach Running status:
```
kubectl get pods -w
```
If the pod status is not Running check the container logs, like so:
```
kubectl logs eureka-svc1-dep-589878c6fb-tzfws
```