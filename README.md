
# EZRA Loan API 
> A simple API that simulates a Lending/Repayment API
  

The Project makes use of: 
 - In-memory H2 Database to create the Database and Tables on the fly.
 - Project Lombok to aid in writing of boilerplate code
 - Hibernate to interface the Database Layer
 - Twilio - 3rd Party SMS Provider


## Installation

### Prerequisites

```

- Spring Boot v2.0+
- JDK, preferably v1.8+
- Maven Dependency manager
- JUnit 5 - The most popular and widely used testing framework for Java
- Lombok - Convenience library for reducing boilerplate code
- Any IDE that supports Java & Spring Boot (IntelliJ, VSC, NetBeans, etc.)
- Postman, curl or any HTTP client

```



## Installation and Running the Project
```sh
1. Clone the repo from Git to your designated folder.
2. Download and install all the required dependencies(Listed above).
3. Run the application with your preferred IDE or via command: `mvn spring-boot:run`
4. This will instantiate the in-memory H2 Database and 
5. This will start tomcat server on default port `8080`

```

## Sample Endpoints Created & Responses

### Lend Loan to Subscriber
Sample `request` sent to the endpoint

```http
POST  http://<yourlocalhosturl>/api/loans/apply
```
The above endpoint allows the Subscriber to apply for loan

#### Sample Request Body
```json
 {
    "subcriberMsisdn":"712345671",
    "amount":"2000",
    "repayAmount": 2005,
    "dueDate": "2023-02-17"

}
```

#### Success Response 200 OK
```json
{
    "status": true,
    "message": "Loan of amount 2000 Disbursed",
    "body": {
        "id": 1,
        "subscriber": 1,
        "amount": 2000,
        "subcriberMsisdn": "712345671",
        "loanDate": "2023-02-16",
        "dueDate": "2023-02-17",
        "repayAmount": 2005,
        "loanStatus": "APPROVED"
    },
    "timestamp": "2023-02-16T18:29:56.890083"
}
```
### Repayment Request from Subscriber
Sample `request` sent to the endpoint

```http
PUT  http://<yourlocalhosturl>/api/loans/repay{loanreferenceNumber}
```
The above endpoint allows the Subscriber to repay their loan

#### Sample Request Body
```json
 {
    "amount": 2005
}
```

#### Success Response 200 OK
```json
{
    "status": true,
    "message": "Loan repayment of amount: 2005 is succesfully done.",
    "body": {
        "id": 1,
        "subscriber": 1,
        "amount": 2000.00,
        "subcriberMsisdn": "711",
        "loanDate": "2023-02-16",
        "dueDate": "2023-02-17",
        "repayAmount": 2000.00,
        "loanStatus": "REPAID"
    },
    "timestamp": "2023-02-16T18:29:56.890083"
}
```
#

## Accessing the Swagger - OpenAPI Documentation

- Once the application is running we will use the base URL to access the documentation.

For example, our base url ```http://localhost:8080/swagger-ui.html#/```


#


## Built With

* [Spring Boot](https://spring.io/projects/spring-boot/) - The web framework used
* [H2 Database](https://www.h2database.com/html/main.html) - The In-memory Database


## Meta

Developed by Ian Ombija


