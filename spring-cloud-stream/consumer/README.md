# Consumer
Run:
```
./gradlew bootRun
```

# Container build
## Dockerfile
Multilayered build:
```
docker build . -t registry.localhost:5000/consumer
```
Push to local registry:
```
docker push registry.localhost:5000/consumer
```
# Deploy to k8s
If you do not have a local k8s cluster, check this [suggestion](../README.md).
```
kubectl apply -f manifests/deployment.yaml
```
Wait for consumer pod to reach Running status:
```
kubectl get pods -w
```
If the pod status is not Running check the logs, like so:
```
kubectl logs consumer-dep-784d6cf78c-5fvfq
```

## Validate

Check that any messages from the [producer](./../producer/README.md) are received.
