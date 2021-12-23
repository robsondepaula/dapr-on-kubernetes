# Spring boot on kubernetes

This repo contains experiments on running a containerized Spring Boot based service on kubernetes.

## Setup a local cluster using k3d
To setup a local cluster with [k3d](https://k3d.io/v5.2.2/#installation), you can follow the instructions on [local-registry](./local-registry/README.md). It will also setup an optional local docker registry.

## Service One (svc1)
A simple Spring Boot [service](./svc1/README.md) that responds to a GET request.