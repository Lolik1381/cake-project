--liquibase formatted sql

--changeset Radzhabova Yuliya:insert_bucket
create table bucket(
    user_data_id varchar(36) references user_date,
    product_id varchar(36) references product,
    number_product bigint not null,
    primary key (user_data_id, product_id)
);