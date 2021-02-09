use bank;

insert into address (address, postal_code, city, country) values
('calle encina, 23', 23400, 'Ubeda', 'Spain');


insert into user (name) values
('Antonio'),
('Jesús');

insert into role (name, user_id) values
('ADMIN', 1),
('ACCOUNT_HOLDER', 2),
('THIRD_PARTY', 1);

insert into account_holder (id,birth,address, mailing_address) values
(1,'1988-12-14',1, 1),
(2,'1987-07-12',1, 1);


insert into account (creation_date, balance_amount, balance_currency, primary_owner, secondary_owner) values
('2021-02-09', 1000, 'USD', 1, 2);

insert into checking (id, secret_key, minimum_balance_amount,minimum_balance_currency, monthly_maintenance_fee_amount, monthly_maintenance_fee_currency, status) values
(1, 'abc',1000,'USD', 12, 'USD','FROZEN');
