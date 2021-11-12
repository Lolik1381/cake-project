--liquibase formatted sql

--changeset Radzhabova Yuliya:create_file
create table file (
    id varchar(36) primary key,
    image bytea
);

--changeset Radzhabova Yuliya:create_product_type
create table product_type (
    id varchar(36) primary key,
    name varchar(255) not null
);

--changeset Radzhabova Yuliya:create_product
create table product (
    id varchar(36) primary key,
    name varchar(255) not null,
    cost bigint not null,
    description varchar(2024),
    composition varchar(2024),
    worth varchar(2048),
    file_id varchar(36) references file,
    product_type_id varchar(36) references product_type,
    weight numeric(2, 2)
);

--changeset Radzhabova Yuliya:create_delivery_interval
create table delivery_interval (
    id varchar(36) primary key,
    name varchar(255) not null
);

--changeset Radzhabova Yuliya:create_delivery_type
create table delivery_type (
    id varchar(36) primary key,
    name varchar(255) not null
);

--changeset Radzhabova Yuliya:create_city
create table city (
    id varchar(36) primary key,
    short_description varchar(255) not null,
    address varchar(255)
);

--changeset Radzhabova Yuliya:create_user_date
create table user_date (
    id varchar(36) primary key,
    name varchar(255) not null,
    lastname varchar(255) not null,
    patronymic varchar(255),
    email varchar(255),
    phone varchar(255),
    city_id varchar(36) references city,
    delivery_type_id varchar(36) references delivery_type,
    street varchar(255),
    house varchar(255),
    entrance varchar(255),
    floor varchar(255),
    apartment_office varchar(255),
    intercom varchar(255),
    comment varchar(1024),
    delivery_interval_id varchar(36) references delivery_interval,
    delivery_date timestamp
);