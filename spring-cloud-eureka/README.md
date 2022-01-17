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
This looks overengineered (since in k8s one could achieve the same by leveraging the *Service* resource) however it shows how one can deploy to Kubernetes a working service discovery solution using Netflix Eureka. To set up the Eureka server follow [eureka-server](./eureka-server/README.md).

Once the Eureka server is in place, follow the "deployment to k8s" sections in [eureka-svc1](./eureka-svc1/README.md) and [eureka-svc2](./eureka-svc2/README.md) to get each service deployed to the kubernetes cluster. You will notice how *eureka-svc2* uses *eureka-server* to resolve the address and then communicates with *eureka-svc1*.
