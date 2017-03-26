# MONEY TRANSFER SERVICE

Sample project for performing money transfering operations.

## USED TECHNOLOGIES

* Embedded Jetty Container 9.4.2.v20170220
* Jersey (JAX-RS implementation) 2.25
* Plain JDBC
* In-memory HSQLDB 1.8.0.7
* TestNG 6.10
* Maven 3.3.9

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

```
[GET] /users -- return list of all users
[POST] /users -- creates new user (name is taken from JSON body)
```
### Account Controller
```
[GET] /accounts -- return list of all accounts
[GET] /accounts/{accountId} -- return info on account with specific accountId
[POST] /accounts -- creates new account belonging to existing user (no unassigned accounts allowed)
```
### Transaction Controller
```
[POST] /transactions -- performs transaction (details are taken from JSON body)
```
## CURL EXAMPLES

Get list of users
```
curl -i -X GET http://localhost:8080/users
```
Create new user
```
curl -i -X POST -d '{"name":"sampleUserName"}' -H "Content-Type: application/json" http://localhost:8080/users
```
Get list of accounts
```
curl -i -X  GET http://localhost:8080/accounts
```
Get info on specific account
```
curl -i -X  GET http://localhost:8080/accounts/1
```
Create account for existing user
```
curl -i -X POST -d '{"balance":100.0, "userId":"1"}' -H "Content-Type: application/json" http://localhost:8080/accounts
```
Perform transfer of specific amount from account to account
```
curl -i -X POST -d '{"fromAccountId":0, "toAccountId":"1", "amount":44.44}' -H "Content-Type: application/json" http://localhost:8080/transactions
```
