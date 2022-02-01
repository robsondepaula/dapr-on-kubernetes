# Dapr state store
Experimenting Dapr state store using MongoDB (currently marked as stable).

## MongoDB
```
docker-compose up -d
```

## Run as Dapr app
```
dapr run --app-id crud-svc --components-path ../dapr-components -- uvicorn main:app
```
