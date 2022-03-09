# Simple CRUD service in Python and DAPR


## Development
If not yet done, create a virtual environment (using [pyenv](https://opensource.com/article/20/4/pyenv), for instance):
```
python -m venv venv
```
Activate the virtual environment:
```
source ./venv/bin/activate
```
Install dependencies:
```
pip install -r requirements.txt
```
Run local server with with DAPR sidecar:
```
dapr run --app-id crud-svc --components-path ../dapr-components -- uvicorn main:app
```
While running you can check [FastAPI's Open API docs](localhost:8000/docs)
