from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import uuid

app = FastAPI()

class Item(BaseModel):
    name: str
    price: float

fake_db = {}

@app.get("/items")
async def read_item():
    return fake_db

@app.get("/items/{item_id}")
async def read_item(item_id: uuid.UUID):
    if item_id in fake_db:
        return fake_db[item_id]
    else:
        raise HTTPException(status_code=404, detail="Item with given id does not exists")

@app.post("/items")
async def create_item(item: Item):
    key = uuid.uuid4()
    fake_db[key] = item
    return key