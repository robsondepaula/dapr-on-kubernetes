import os
from fastapi import Request, FastAPI
import httpx

dapr_port = os.getenv("DAPR_HTTP_PORT")
if dapr_port == None:
    print("This script needs to run with dapr")
    exit()

state_url = f"http://localhost:{dapr_port}/v1.0/state/mongostore"

app = FastAPI()

@app.get("/items/{item_key}")
async def read_item(item_key: str):
    r = httpx.get(f"{state_url}/{item_key}")
    return r.content

@app.post("/items/{item_key}")
async def create_item(request: Request, item_key: str):
    body = await request.json()
    dapr_state = f'[ {{ "key": "{item_key}", "value": "{body}" }} ]'
    r = httpx.post(f"{state_url}", data=dapr_state)
    return r.content