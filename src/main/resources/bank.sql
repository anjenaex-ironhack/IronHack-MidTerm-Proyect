DROP SCHEMA bank;
CREATE SCHEMA bank;
USE bank;

CREATE TABLE address (
id BIGINT,
address VARCHAR(255),
postal_code INT,
city VARCHAR(255),
country VARCHAR(255),
PRIMARY KEY (id)
);

CREATE TABLE user (
id BIGINT,
name VARCHAR(255),
role VARCHAR(255),
PRIMARY KEY (id)
);

CREATE TABLE admin (
id BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user(id)
);

CREATE TABLE account_holder (
id BIGINT,
birth DATE,
address BIGINT,
mailing_address VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user(id),
FOREIGN KEY (address) REFERENCES address(id)
);
drop table account_holder;

CREATE TABLE third_party (
id BIGINT,
hashed_key VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user(id)
);

CREATE TABLE money (
id BIGINT,
currency VARCHAR(255),
amount DECIMAL,
PRIMARY KEY(id)
);
drop table money;

CREATE TABLE account (
id BIGINT,
balance BIGINT,
primary_owner BIGINT,
secondary_owner BIGINT,
PRIMARY KEY(id),
FOREIGN KEY(balance) REFERENCES money(id),
FOREIGN KEY(primary_owner) REFERENCES account_holder(id),
FOREIGN KEY(secondary_owner) REFERENCES account_holder(id)
);
drop table account;

CREATE TABLE credit_card (
id BIGINT,
credit_limit BIGINT,
interest_rate DECIMAL,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id),
FOREIGN KEY (credit_limit) REFERENCES money(id)
);

CREATE TABLE checking (
id BIGINT,
secret_key VARCHAR (255),
minimum_balance BIGINT,
monthly_maintenance_fee BIGINT,
credit_card BIGINT,
status VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id),
FOREIGN KEY (minimum_balance) REFERENCES money(id),
FOREIGN KEY (monthly_maintenance_fee) REFERENCES money(id),
FOREIGN KEY (credit_card) REFERENCES credit_card(id)
);

CREATE TABLE student_checking (
id BIGINT,
secret_key VARCHAR (255),
status VARCHAR(255),
credit_card BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id),
FOREIGN KEY (credit_card) REFERENCES credit_card(id)
);

CREATE TABLE saving (
id BIGINT,
secret_key VARCHAR (255),
interest_rate DECIMAL,
status VARCHAR(255),
credit_card BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id),
FOREIGN KEY (credit_card) REFERENCES credit_card(id)
);


CREATE TABLE account_holder_address (
account_holder_id BIGINT,
address_id BIGINT,
PRIMARY KEY(account_holder_id, address_id),
FOREIGN KEY(account_holder_id) REFERENCES account_holder(id),
FOREIGN KEY(address_id) REFERENCES address(id)
);








