# Java Customer Connect

I just completed CustomerConnect, a customer service management system
customers using Spring Boot and Spring Data JPA!

In the project, I implemented RESTful endpoints capable of paging, sorting and filtering.

##  Dependencies and Technologies

- Java 21
- Spring Boot
- Spring Web
- Spring JPA
- MYSQL
- Docker to Connect with MYSQL

## Features
I implemented essential features such as:
- Create Customer
- Update Customer
- Get Customer by ID 
- Get All Customers 
  - Paginating the result
  - Filter by CPF, Email or both CPF and Email
  - Order by Desc or Asc by param  
- Delete Customer

## Execute the project

- Clone the project

```bash
  git clone https://github.com/jpllopes96/java-todo-list.git
```
- Make sure you have docker running

- Open the terminal and run the docker-compose.yml file
```bash
  cd $yourMachineLocation/customerconnect/docker/
  docker compose up
```
- Database properties are located under docker-compose.yml and application.properties

- Open the project in your IDE and run it.

- Consume the API endpoints using an API Client(Postman, Bruno, Insomnia)

- Use a database manager to see the data on Database.

## Endpoints
 - POST /customers: Endpoint to create customers.
   - Required parameters:
     - name: Full name
     - cpf(Unique): CPF Number
     - email(Unique): Email
     - phoneNumber(Unique): Phone Number
     
 - GET /customers: Endpoint to list the customers
   - Parameters:
     - page: page number
     - pageSize: items by page.
     - orderBy: Order by create date - ASC or Desc
     - email: Filter by Email
     - cpf: Filter by CPF
   
 - PUT /customers/{customerId}: Endpoint to update a customer.
     - Parameters we can update:
       - name
       - email
       - phoneNumber
 
 - DELETE /customers/{customerId}: Endpoint to delete a customer
## Demo

<img alt="Get Request" src="assets/images/demo.png" width="100%">


## Find me

- 🌍 [Linkedin](https://www.linkedin.com/in/joao-lopes-071026198/)
