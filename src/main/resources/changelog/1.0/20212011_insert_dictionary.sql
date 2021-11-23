--liquibase formatted sql

--changeset Radzhabova Yuliya:insert_city
insert into city(id, short_description) VALUES (1, 'Москва');

--changeset Radzhabova Yuliya:insert_delivery_type
insert into delivery_type(id, name) VALUES (1, 'Доставка по Москве');

--changeset Radzhabova Yuliya:insert_delivery_interval
insert into delivery_interval(id, name) VALUES (1, 'C 12:00 до 15:00');