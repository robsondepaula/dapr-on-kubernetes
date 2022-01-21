# Spring Cloud Stream

Experimenting the Spring Cloud Stream (with RabbitMQ).

## RabbitMQ
```
docker-compose up -d
```
Visit http://localhost:15672/ and login with the user and password you defined in the .env file to gain access to the management console.

# Kubernetes
Use the *RabbitMQ Cluster Operator* to properly deploy the RabbitMQ into the k8s cluster:
```
kubectl apply -f "https://github.com/rabbitmq/cluster-operator/releases/latest/download/cluster-operator.yml"
```
Once it is deployed and available, apply the [definition.yaml](./definition.yaml) to create a RabbitMQ instance:
```
kubectl apply -f definition.yaml
```
Verify all is well:
```
kubectl get all -l app.kubernetes.io/name=definition
```
Output must be somethink like:
```
NAME                      READY   STATUS    RESTARTS   AGE
pod/definition-server-0   1/1     Running   0          56s

NAME                       TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)                        AGE
service/definition-nodes   ClusterIP   None           <none>        4369/TCP,25672/TCP             56s
service/definition         ClusterIP   10.43.146.64   <none>        5672/TCP,15672/TCP,15692/TCP   56s

NAME                                 READY   AGE
statefulset.apps/definition-server   1/1     56s
```

## Retrieving RabbitMQ credentials and dashboard access
```
kubectl -n default get secret definition-default-user -o jsonpath="{.data.username}" | base64 --decode
```
```
kubectl -n default get secret definition-default-user -o jsonpath="{.data.password}" | base64 --decode
```
```
kubectl port-forward service/definition 50524:15672
```
Visit [http://localhost:50524/](http://localhost:50524/) and use the credentials.
