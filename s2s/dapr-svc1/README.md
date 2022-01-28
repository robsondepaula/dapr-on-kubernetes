# Self hosted
Check DAPR section on [svc1](../svc1/README.md)

# Deploy to k8s
Check the necessary [annotations](https://docs.dapr.io/operations/hosting/kubernetes/kubernetes-overview/) to make this [deployment](./manifests/deployment.yaml) "dapr enabled".

Proceed with the deployment by:
```
kubectl apply -f manifests/deployment.yaml
```
Wait for dapr-svc1 pod to reach Running status:
```
kubectl get pods -w
```
If the pod status is not Running check the app container logs, like so:
```
kubectl logs dapr-svc1-dep-66d69b779-w99xg dapr-svc1-container
```
And perhaps the dapr sidecar logs might give good hints too:
```
kubectl logs dapr-svc1-dep-66d69b779-w99xg daprd
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
dapr-svc1-dapr   ClusterIP   None            <none>        80/TCP,50001/TCP,50002/TCP,9090/TCP   90s
dapr-svc1-svc    NodePort    10.43.38.144    <none>        2345:30079/TCP                        5s
```
And finally:
```
curl localhost:30079/hello
```