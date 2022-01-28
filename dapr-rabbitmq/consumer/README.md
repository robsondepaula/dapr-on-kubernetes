1. Build:
```
./gradlew clean build
```
2. Run:
```
dapr run --app-id dapr-consumer --app-port 8081 --dapr-http-port 9092 --components-path ./dapr-components -- java -jar build/libs/consumer-0.0.1-SNAPSHOT.jar
```
3. Validate:

Use the [requests](./requests/consumer.rest) file for an isolated test.

Or inspect logs after validating the producer.

# Container build
## Dockerfile
Multilayered build:
```
docker build . -t registry.localhost:5000/dapr-rmq-consumer
```
Push to local registry:
```
docker push registry.localhost:5000/dapr-rmq-consumer
```
# Deploy to k8s (with dapr sidecar)
Check the necessary [annotations](https://docs.dapr.io/operations/hosting/kubernetes/kubernetes-overview/) to make this [deployment](./manifests/deployment.yaml) "dapr enabled".

Create the DAPR PubSub subscription for RabbitMQ:
```
kubectl apply -f manifests/subscription.yaml
```
Proceed with the deployment by:
```
kubectl apply -f manifests/deployment.yaml
```
Wait for dapr-consumer pod to reach Running status:
```
kubectl get pods -w
```
If the pod status is not Running check the app container logs, like so:
```
kubectl logs dapr-consumer-dep-68fc767df8-2n8kw dapr-consumer-container
```
And perhaps the dapr sidecar logs might give good hints too:
```
kubectl logs dapr-consumer-dep-68fc767df8-2n8kw daprd
```
## Validate
Inspect logs after validating the [producer](../producer/README.md).