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


CREATE TABLE third_party (
id BIGINT,
hashed_key VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user(id)
);


CREATE TABLE account (
id BIGINT,
balance_amount decimal,
balance_currency VARCHAR(255),
primary_owner BIGINT,
secondary_owner BIGINT,
PRIMARY KEY(id),
FOREIGN KEY(primary_owner) REFERENCES account_holder(id),
FOREIGN KEY(secondary_owner) REFERENCES account_holder(id)
);


CREATE TABLE credit_card (
id BIGINT,
credit_limit_amount decimal,
credit_limit_currency varchar(255),
interest_rate DECIMAL,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id)
);

CREATE TABLE checking (
id BIGINT,
secret_key VARCHAR (255),
minimum_balance_amount decimal,
minimum_balance_currency varchar(255),
monthly_maintenance_fee_amount decimal,
monthly_maintenance_fee_currency varchar(255),
status VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id)
);

CREATE TABLE student_checking (
id BIGINT,
secret_key VARCHAR (255),
status VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id)
);

CREATE TABLE saving (
id BIGINT,
secret_key VARCHAR (255),
interest_rate DECIMAL,
status VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id)
);


CREATE TABLE account_holder_address (
account_holder_id BIGINT,
address_id BIGINT,
PRIMARY KEY(account_holder_id, address_id),
FOREIGN KEY(account_holder_id) REFERENCES account_holder(id),
FOREIGN KEY(address_id) REFERENCES address(id)
);








