# MidTerm project: AppBank

This project is an API for a potential bank.

The API includes the option to create different kind of Account: Checking, StudentChecking, Saving and CreditCard.
Each have different properties.

There is there kind of user in the system: Admin, AccountHolder and ThirdParty

At the moment the API is not finihed, so all tests, thirParties transactions (in process at the moment) and securities (is in process at the moment) fail.
The last "working" version, where you can create different accounts, check info and make transactions is in the tag version: 0.5.

The API also includes fraud detection, and automatic management of interestRate.

## Installation

1. Download the project from the repository.

2. Open the directory as a project on a IDE as IntelliJ.

3. Go into your application.properties and add or replace the line `spring.datasource.url`

```bash   
spring.datasource.url=jdbc:mysql://localhost:3306/bank?serverTimezone=UTC
```

4. Also in application.properties update the lines
   `spring.datasource.username=username` and `spring.datasource.password=password` with your username and password (NOT TO BE CONFUSED WITH THE LOGIN CREDENTIALS FOR THE ACTUAL APP).
   
## Usage

This API doesn't have an interface, you should insert the data into a database and then check into postman the routes of
the functionalities. 

In this project, into the resources folder, there is a "queries.sql" and a AppBank collection for Postman.

## Routes

- Use localhost:8080 with all the routes


- **GET**

- `/accounts` : get all the accounts, access admin.
- `/accounts/name/{name}` : get all the accounts with a specific, access admin.
- `/account/id/{id}` : get a specific account by id, access admin, AccountHolder (only if the AccountHolder owns the account).
- `/account/balance/{id}` : get a specific balance by id, access admin, AccountHolder (only if the AccountHolder owns the account).
- `/user/account-holders` : get all the AccountsHolders, access admin.
- `/user/account-holder` : get an AccountHolder by id or dni, access admin. This route has two optional params, id and dni.


- **POST**

- `/create/account-holder` : create an accountHolder, access admin. Address and roles should be added manually into the database.
- `/create/checking-account` : create a checkingAccount (or a StudentCheckingAccount if the primaryAccountHolder is lower than 18 years old), access admin.
- `/create/saving-account` : create an saving account, access admin.
- `/create/credit-card` : create an credit card account, access admin.
- `/account/transfer-money/{id}` : send money from an account to another account and create a transaction. accountHolder access. You need to send as params, the id of the beneficiary account, the id of one of the accountHolders and the amount you want to transfer.


- **PATCH**

- `/account/{id}/balance` : update a balance, access admin.
- `/user/third-party/send-money/{id}`: send money from a thirdParty to an account. thirdParty access. thirdParty access. You need to send the hashed key of the thirdParty as header, and also the params id of the account who get the money and the amount.
- `/user/third-party/receive-money/{id}`: send money from an account to a thirdParty and create a transaction. accountHolder access. You need to send the hashed key of the thirdParty as header, and also the params id of the account who pais and the amount.


## Authors

**Antonio Jesús Navarro Expósito**
