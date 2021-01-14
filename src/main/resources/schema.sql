CREATE TABLE parameter_exchange
(id  INTEGER ,
 exchange_rate DOUBLE,
 origin_money VARCHAR(3),
 destiny_money VARCHAR(3));

insert into parameter_exchange(id,exchange_rate,origin_money,destiny_money)
values(1, 3.5 ,'USD','PER');

insert into parameter_exchange(id,exchange_rate,origin_money,destiny_money)
values(2, 4.2 ,'EUR','PER' );

insert into parameter_exchange(id,exchange_rate,origin_money,destiny_money)
values(4, 0.2, 'MEX','PER' );

insert into parameter_exchange(id,exchange_rate,origin_money,destiny_money)
values(5, 0.328 ,'PER','EUR' );

insert into parameter_exchange(id,exchange_rate,origin_money,destiny_money)
values(6, 3.5, 'BRA','CAN' );

insert into parameter_exchange(id,exchange_rate,origin_money,destiny_money)
values(7, 3.5, 'BRA','MEX' );