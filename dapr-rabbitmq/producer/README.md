1. Build:
```
./gradlew clean build
```
2. Run:
```
dapr run --app-id dapr-producer --app-port 8080 --dapr-http-port 9091 --components-path ./dapr-components -- java -jar build/libs/producer-0.0.1-SNAPSHOT.jar
```
3. Validate:
Use the [requests](./requests/producer.rest) file for an isolated test.

```
curl -X POST http://localhost:9091/v1.0/invoke/dapr-producer/method/produce \
   -H "Content-Type: application/json" \
   -d '{"productId": 123456, "quantity": 100}'
```
Or:
```
dapr publish --publish-app-id dapr-producer --pubsub rabbitmq --topic ardp-topic --data '{"productId": 123456, "quantity": 100}'
```
# Container build
## Dockerfile
Multilayered build:
```
docker build . -t registry.localhost:5000/dapr-rmq-producer
```
Push to local registry:
```
docker push registry.localhost:5000/dapr-rmq-producer
```
# Deploy to k8s (with dapr sidecar)
Check the necessary [annotations](https://docs.dapr.io/operations/hosting/kubernetes/kubernetes-overview/) to make this [deployment](./manifests/deployment.yaml) "dapr enabled".

Create the DAPR PubSub component for RabbitMQ:
```
kubectl apply -f manifests/component.yaml
```
Proceed with the deployment by:
```
kubectl apply -f manifests/deployment.yaml
```
Wait for dapr-producer pod to reach Running status:
```
kubectl get pods -w
```
If the pod status is not Running check the app container logs, like so:
```
kubectl logs dapr-producer-dep-7c49695b7b-k7mpp dapr-producer-container
```
And perhaps the dapr sidecar logs might give good hints too:
```
kubectl logs dapr-producer-dep-7c49695b7b-k7mpp daprd
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
dapr-rmq-producer-dapr   ClusterIP   None            <none>        80/TCP,50001/TCP,50002/TCP,9090/TCP   6m29s
dapr-producer-svc        NodePort    10.43.190.148   <none>        2352:30004/TCP                        4m11s
```
And finally:
```
curl -X POST http://localhost:30004/produce \
   -H "Content-Type: application/json" \
   -d '{"productId": 123456, "quantity": 100}'
```
The logs from the [consumer](../consumer/README.md) app will show something similar to:
```
Dapr consumer called!
{pubsubname=rabbitmq, traceid=00-1c2a27a820a7680c791adc8e199e0589-145bf1eea673ba28-01, id=34e2826b-f139-4836-a878-60277a41acf6, source=dapr-rmq-producer, type=com.dapr.event.sent, data={quantity=100, productId=123456}, specversion=1.0, datacontenttype=application/json, topic=ardp-topic}
```
