DROP SCHEMA bank;
CREATE SCHEMA bank;
USE bank;

create table address (
id bigint,
address varchar(255),
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

create table third_party (
id bigint,
hashed_key varchar(255),
primary key (id),
foreign key (id) references user(id)
);

create table money (
id bigint,
currency varchar(255),
amoung decimal,
PRIMARY KEY(id)
);

CREATE TABLE account (
id BIGINT,
balance bigint,
primary_owner bigint,
secondary_owner bigint,
PRIMARY KEY(id),
foreign key(balance) references money(id),
foreign key(primary_owner) references account_holder(id),
foreign key(secondary_owner) references account_holder(id)
);

create table creditCard (
id bigint,
credit_limit bigint,
interest_rate decimal,
primary key (id),
foreign key (id) references account(id),
foreign key (credit_limit) references money(id)
);

create table checking (
id bigint,
secret_key varchar (255),
minimum_balance bigint,
monthly_maintenance_fee bigint,
status varchar(255),
primary key (id),
foreign key (id) references account(id),
foreign key (minimum_balance) references money(id),
foreign key (monthly_maintenance_fee) references money(id)
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




-- create table account_money (
-- account_id bigint,
-- money_id bigint,
-- primary key(account_id, money_id),
-- foreign key(account_id) references account(id),
-- foreign key(money_id) references money(id)
-- );

create table account_account_holder (
account_id bigint,
account_holder_id bigint,
primary key(account_id, account_holder_id),
foreign key(account_id) references account(id),
foreign key(account_holder_id) references account_holder(id)
);

create table account_holder_address (
account_holder_id bigint,
address_id bigint,
primary key(account_holder_id, address_id),
foreign key(account_holder_id) references account_holder(id),
foreign key(address_id) references address(id)
);








