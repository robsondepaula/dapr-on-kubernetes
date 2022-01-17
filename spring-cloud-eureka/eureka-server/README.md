# Eureka Server
A service registry that allows service discovery.

After:
```
./gradlew bootRun
```
Visit http://localhost:8761/.

# Container build
## Dockerfile
Multilayered build:
```
docker build . -t registry.localhost:5000/eureka-server
```
Push to local registry:
```
docker push registry.localhost:5000/eureka-server
```

# Deploy to k8s

Proceed with the creation of a StatefulSet by:
```
kubectl apply -f manifests/statefulset.yaml
```
Wait for eureka-server pod to reach Running status:
```
kubectl get pods -w
```
If the pod status is not Running check the container logs, like so:
```
kubectl logs eureka-sfs-0 
```
Deploy the NodePort service:
```
kubectl apply -f manifests/service.yaml
```
Verify the service is available:
```
kubectl get service
```
Should output something like:
```
eureka-server-service   NodePort    10.43.118.206   <none>        2349:30001/TCP                        32s
```
