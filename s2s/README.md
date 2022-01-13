# k8s
Use k3d to create a k3s cluster and allow it to use the [local registry](../local-registry/README.md):
```
k3d cluster create \
    --servers 1 \
    --agents 1 \
    --volume $PWD/../local-registry/k3d-registries.yaml:/etc/rancher/k3s/registries.yaml \
    --k3s-arg "--disable=traefik@server:0" \
    -p "30000-30100:30000-30100@server:0"
```
For a refresher about exposing services on k3d, check  https://k3d.io/v5.2.2/usage/exposing_services/.
```
docker network connect k3d-k3s-default registry.localhost
```

# Reference
https://github.com/rancher/k3d/issues/359

# Plain Spring on k8s
Follow the deployment to k8s sections in [svc1](./svc1/README.md) and [svc2](./svc2/README.md) to get each service deployed to the kubernetes cluster.

# With dapr
Deploy [dapr](https://docs.dapr.io/operations/hosting/kubernetes/kubernetes-deploy/) to the cluster, when finished, setup [Zipkin in k8s mode](https://docs.dapr.io/operations/monitoring/tracing/setup-tracing/).

After completing the guides above, proceed with [dapr-svc1](./dapr-svc1/README.md) and [dapr-svc2](./dapr-svc2/README.md).

## Zipking dashboard
View tracing data by:
```
kubectl port-forward svc/zipkin 9411:9411
```

## Dapr dashboard
The dapr dashboard can be used to get an overview of what is running:
```
dapr dashboard -k -p 9999
```
