# at-employee
Backend Spring REST API coding exercise

java version: 11

maven version: 3.6.3

dependency: 
- H2
- Liquibase
- MapStruct
- Swagger
- JWT
- Docker

default port: 8080

api:
- GET /v1/employee
- GET /v1/employee/{userId}
- POST /v1/employee
- PATCH /v1/employee
- DELETE /v1/employee/{userId}


you can use the command below to build and run on Docker (port:9090)
>$ docker build -t at-employee .
>
>$ docker run -p 9090:8080 at-employee

and check out the Postman API to access collections pass thouht this link
>https://www.getpostman.com/collections/2fc018c8a86e49b3f461
