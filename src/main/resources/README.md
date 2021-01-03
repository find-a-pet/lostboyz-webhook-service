# Lostboyz Webhook Service

## Usage

All responses will have the form

````
{"id":"UUID of the Pet","_links":{"self":{"href":"The Pet with the above specified UUID"},"pets":{"href":"List of all pets"}}}
````

**Definitions**

### Return a new lost pet

This service will receive a JSON payload at `/webhook`.
The payload will contain data to create a new missing pet in the lostboyz-pet-service.

`POST /webhook`

**Response**
- `201 CREATED` on success

````
{"id":"0de65864-fa6c-413f-9769-5f01883c0e7a","_links":{"self":{"href":"http://localhost:14005/api/v1/pets/0de65864-fa6c-413f-9769-5f01883c0e7a"},"pets":{"href":"http://localhost:14005/api/v1/pets"}}}
````