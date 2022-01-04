# Producer
Run:
```
./gradlew bootRun
```
Validate:
```
curl -X POST http://localhost:8989/publish \
   -H "Content-Type: application/json" \
   -d '{"productId": 123456, "quantity": 100}'
```