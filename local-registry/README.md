# Local docker registry
```
docker volume create local_registry
```
```
docker container run -d --name registry.localhost \
    -v local_registry:/var/lib/registry \
    --restart always \
    -p 5000:5000 \
    registry:2
```
## k3s usage
Add the name resolution to */etc/hosts*:
```
# To resolve local docker registry in k3s
127.0.0.1 registry.localhost
```