from fastapi import Request, FastAPI
from dapr.clients import DaprClient

app = FastAPI()

STATE_STORE_NAME = "mongostore"

@app.get("/items/{item_key}")
async def read_item(item_key: str):
    with DaprClient() as d:
        data = d.get_state(store_name=STATE_STORE_NAME, key=f"{item_key}").data
        return data

@app.post("/items/{item_key}")
async def create_item(request: Request, item_key: str):
    body = await request.json()
    with DaprClient() as d:
        d.save_state(STATE_STORE_NAME, f"{item_key}", f"{body}") 
        data = d.get_state(store_name=STATE_STORE_NAME, key=f"{item_key}").data
        return data