# Kubernetes hands-on

This repo contains experiments on running containerized services on kubernetes.

## Setup a local cluster using k3d
To setup a local cluster with [k3d](https://k3d.io/v5.2.2/#installation), you can follow the instructions on [local-registry](./local-registry/README.md). It will also setup an optional local docker registry.

## Services
On the subfolders, you can find various "micro-services" (pun intended) used as scaffolding for learning Spring, DAPR, FastApi, etc..

## .rest files
If it is the first time you see a ".rest" file, it is one of the supported file extensions for the excellent [Rest Client](https://marketplace.visualstudio.com/items?itemName=humao.rest-client).

## DAPR
### Zipkin
Each sidecar populates the self-hosted Zipkin by default. 

Check the tracing by visiting http://localhost:9411/zipkin.

### Dapr dashboard
The dapr dashboard can be used to get an overview of what is running:
```
dapr dashboard -p 9988
```
