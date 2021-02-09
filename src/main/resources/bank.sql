DROP SCHEMA bank;
CREATE SCHEMA bank;
USE bank;

CREATE TABLE address (
id BIGINT not null auto_increment,
address VARCHAR(255),
postal_code INT,
city VARCHAR(255),
country VARCHAR(255),
PRIMARY KEY (id)
);

insert into address (address, postal_code, city, country) values
('calle encina, 23', 23400, 'Ubeda', 'Spain');

create table role (
name VARCHAR(255),
primary key (name)
);
insert into role (name) values
('ADMIN'),
('ACCOUNT_HOLDER'),
('THIRD_PARTY');

CREATE TABLE user (
id BIGINT not null auto_increment,
name VARCHAR(255),
PRIMARY KEY (id)
);

create table user_role (
user_id bigint,
role_id varchar(255),
primary key(user_id, role_id),
foreign key(role_id) references role(name),
foreign key(user_id) references user(id)
);

insert into user (name) values
('Antonio'),
('Jesús');

insert into user_role (role_id, user_id) values 
('ACCOUNT_HOLDER', 1);

CREATE TABLE admin (
id BIGINT  not null auto_increment,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user(id)
);

CREATE TABLE account_holder (
id BIGINT  not null auto_increment,
birth DATE,
address BIGINT,
mailing_address VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user(id),
FOREIGN KEY (address) REFERENCES address(id)
);

insert into account_holder (birth, address, mailing_address) values
('1988-12-14', 1, 'anjenaex@gmail.com'),
('1987-07-12', 1, 'dfasd@dfasd.com');


CREATE TABLE third_party (
id BIGINT  not null auto_increment,
hashed_key VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user(id)
);


CREATE TABLE account (
id BIGINT  not null auto_increment,
creation_date Date,
balance_amount decimal,
balance_currency VARCHAR(255),
primary_owner BIGINT,
secondary_owner BIGINT,
PRIMARY KEY(id),
FOREIGN KEY(primary_owner) REFERENCES account_holder(id),
FOREIGN KEY(secondary_owner) REFERENCES account_holder(id)
);

insert into account (creation_date, balance_amount, balance_currency, primary_owner, secondary_owner) values
('2021-02-09', 1000, 'USD', 1, 2);


CREATE TABLE credit_card (
id BIGINT  not null auto_increment,
credit_limit_amount decimal,
credit_limit_currency varchar(255),
interest_rate DECIMAL,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id)
);

CREATE TABLE checking (
id BIGINT not null auto_increment,
secret_key VARCHAR (255),
minimum_balance_amount decimal,
minimum_balance_currency varchar(255),
monthly_maintenance_fee_amount decimal,
monthly_maintenance_fee_currency varchar(255),
status VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id)
);

insert into checking (secret_key, minimum_balance_amount,minimum_balance_currency, monthly_maintenance_fee_amount, monthly_maintenance_fee_currency, status) values
('abc',1000,'USD', 12, 'USD','FROZEN');

CREATE TABLE student_checking (
id BIGINT  not null auto_increment,
secret_key VARCHAR (255),
status VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id)
);

CREATE TABLE saving (
id BIGINT  not null auto_increment,
secret_key VARCHAR (255),
interest_rate DECIMAL,
status VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account(id)
);


CREATE TABLE account_holder_address (
account_holder_id BIGINT  not null auto_increment,
address_id BIGINT,
PRIMARY KEY(account_holder_id, address_id),
FOREIGN KEY(account_holder_id) REFERENCES account_holder(id),
FOREIGN KEY(address_id) REFERENCES address(id)
);

select * from account;

select * from account_holder;

select * from checking;

select a.id, a.creation_date, a. balance_amount,u.name, r.name as role, h.birth, c.secret_key, c.minimum_balance_amount, c.monthly_maintenance_fee_amount, status from checking c 
join account a on c.id = a.id
join account_holder h on h.id = a.id
join user u on h.id = u.id
join user_role ur on ur.user_id = h.id
join role r on ur.role_id = r.name;







