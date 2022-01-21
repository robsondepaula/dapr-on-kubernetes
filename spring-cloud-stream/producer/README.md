# Producer
Run:
```
./gradlew bootRun
```
Validate:
```
curl -X POST http://localhost:8989/publish \
   -H "Content-Type: application/json" \
   -d '{"productId": 123456, "quantity": 100}'
```
# Container build
## Dockerfile
Multilayered build:
```
docker build . -t registry.localhost:5000/producer
```
Push to local registry:
```
docker push registry.localhost:5000/producer
```
# Deploy to k8s
If you do not have a local k8s cluster, check this [suggestion](../README.md).
```
kubectl apply -f manifests/deployment.yaml
```
Wait for producer pod to reach Running status:
```
kubectl get pods -w
```
If the pod status is not Running check the logs, like so:
```
kubectl logs producer-dep-55458d8bb9-cfz22
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
producer-svc            NodePort    10.43.189.235   <none>        2351:30003/TCP                        7s
```
And finally:
```
curl -X POST http://localhost:30003/publish \
   -H "Content-Type: application/json" \
   -d '{"productId": 123456, "quantity": 100}'
```