DROP SCHEMA bank;
CREATE SCHEMA bank;
USE bank;

CREATE TABLE account (
id BIGINT,
balance decimal,
primary_owner VARCHAR(255),
secundary_owner VARCHAR(255),
PRIMARY KEY(id)
);

create table checking (
id bigint,
secret_key varchar (255),
minimum_balance decimal,
monthly_maintenance_fee decimal,
status varchar(255),
primary key (id),
foreign key (id) references account(id)
);

create table student_checking (
id bigint,
secret_key varchar (255),
status varchar(255),
primary key (id),
foreign key (id) references account(id)
);

create table saving (
id bigint,
secret_key varchar (255),
interest_rate decimal,
status varchar(255),
primary key (id),
foreign key (id) references account(id)
);

create table creditCard (
id bigint,
credit_limit decimal,
interest_rate decimal,
primary key (id),
foreign key (id) references account(id)
);

create table adress (
id bigint,
adress varchar(255),
postalcode int,
city varchar (255),
country varchar(255),
primary key (id)
);

create table user (
id bigint,
name varchar(255),
role varchar(255),
primary key (id)
);

create table admin (
id bigint,
primary key (id),
foreign key (id) references user(id)
);

create table account_holder (
id bigint,
birth date,
mailingAdress varchar(255),
primary key (id),
foreign key (id) references user(id)
);

create table account_adress (
account_id bigint,
adress_id bigint,
primary key(account_id, adress_id),
foreign key(account_id) references account(id),
foreign key(adress_id) references adress(id)
);

create table third_party (
id bigint,
hashed_key varchar(255),
primary key (id),
foreign key (id) references user(id)
);






