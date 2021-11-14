--liquibase formatted sql

--changeset Radzhabova Yuliya:insert_product_types
insert into product_type(id, name) values (1, 'Бисквитные торты');
insert into product_type(id, name) values (2, 'Детские торты');
insert into product_type(id, name) values (3, 'Классические торты');
insert into product_type(id, name) values (4, 'Оригинальные торты');
insert into product_type(id, name) values (5, 'Торты');

--changeset Radzhabova Yuliya:change_numeric_product
alter table product alter column weight type numeric(10, 2);