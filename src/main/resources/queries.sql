use bank;

insert into address (address, postal_code, city, country) values
('calle encina, 23', 23400, 'Ubeda', 'Spain');


insert into user (name) values
('Antonio'),
('Jesús'),
('Juan');

insert into role (id, name, user_id) values
(1, 'ADMIN', 1),
(2, 'ACCOUNT_HOLDER', 2),
(3, 'THIRD_PARTY', 1);

insert into account_holder (id, dni, birth,address, mailing_address) values
(1,'75115045N','1988-12-14',1, 1),
(2,'12345678A','2005-07-12',1, 1),
(3,'87654321T','2005-07-12',1, 1);


 insert into account (balance_amount, balance_currency,creation_date, primary_owner, secondary_owner) values
 (1000, 'USD','2021-02-13', 1, 2),
 (2000, 'USD','2021-02-13', 2, 1),
 (1000, 'USD','2021-02-13', 3, 1),
 (1000, 'USD','2021-02-13', 2, 3);

 insert into checking (id, secret_key, minimum_balance_amount,minimum_balance_currency, monthly_maintenance_fee_amount, monthly_maintenance_fee_currency, status) values
 (1, 'abc',250,'USD', 12, 'USD','ACTIVE');
 
 insert into student_checking (id, secret_key, status) values
 (2, 'abc','ACTIVE');
 
 insert into saving (id, secret_key, interest_rate, amount, currency, update_date, status) values
 (3, 'abc', 0.5, 1000, 'USD', '2022-02-13', 'ACTIVE');
 
 insert into credit_card (id, credit_limit_amount, credit_limit_currency, interest_rate, update_date) values
 (4, 100, 'USD', 0.1, '2022-02-13');
 
 
