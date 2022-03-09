# Dapr state store
Experimenting Dapr state store (via SDK) using MongoDB (currently marked as stable).

## MongoDB
```
docker-compose up -d
```

## Run as Dapr app
```
dapr run --app-id crud-svc-sdk --components-path ../dapr-components -- uvicorn main:app
```
