# Local docker registry
```
docker volume create local_registry
```
```
docker container run -d --name registry.localhost -v local_registry:/var/lib/registry --restart always -p 5000:5000 registry:2
```
Add the mapping to */etc/hosts*:
```
# To resolve local docker registry in k3s
127.0.0.1 registry.localhost
```

# Kubernetes 
Use k3d to create a k3s cluster and allow it to use the local registry:
```
k3d cluster create --volume $PWD/k3d-registries.yaml:/etc/rancher/k3s/registries.yaml --k3s-arg "--disable=traefik@server:0" -p 8082:30080@agent:0 --agents 2
```
```
docker network connect k3d-k3s-default registry.localhost
```

# Reference
https://github.com/rancher/k3d/issues/359