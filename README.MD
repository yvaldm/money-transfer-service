# MONEY TRANSFER SERVICE

Sample project for performing money transfering operations.

## USED TECHNOLOGIES

* Embedded Jetty
* Jersey (JAX-RS implementation)
* Plain JDBC
* In-memory HSQL
* TestNG

## BUILD

```
$ mvn clean package
$ java -jar ./target/money-transfer-service-1.0-SNAPSHOT.jar

```

## ENTITIES DESCRIPTION

* USER (INTEGER USER_ID, STRING NAME)
* ACCOUNT (INTEGER ACCOUNT_ID, DECIMAL BALANCE, INTEGER USER_ID)

## API DESCRIPTION

### User Controller

[GET] /users -- return list of all users
[POST] /users -- creates new user (name is taken from JSON body)

### Account Controller

[GET] /accounts -- return list of all accounts
[POST] /accounts -- creates new account belonging to existing user (no unassigned accounts allowed)

### Transaction Controller

[POST] /transaction -- performs transaction (details are taken from JSON body)

## CURL EXAMPLES

curl -i -X GET http://localhost:8080/users
curl -i -X POST -d '{"name":"sampleUserName"}' -H "Content-Type: application/json" http://localhost:8080/users


