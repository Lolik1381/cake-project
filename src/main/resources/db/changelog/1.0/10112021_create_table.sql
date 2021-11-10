--liquibase formatted sql

--changeset Stanislav Lychagin:create_table

CREATE TABLE company (
                         id bigserial PRIMARY KEY,
                         prm_id bigint UNIQUE,
                         parent_id bigint REFERENCES company,
                         name varchar(255),
                         egrul_name varchar(1000),
                         egrul_short_name varchar(255),
                         inn varchar(25),
                         dzo_percent decimal,
                         can_sell_product boolean NOT NULL DEFAULT 'true'
);

COMMENT ON TABLE company IS 'КЭС (Компания Экосистемы)';

COMMENT ON COLUMN company.id IS 'Идентификатор';
COMMENT ON COLUMN company.prm_id IS 'Идентификатор компании в SberPRM';
COMMENT ON COLUMN company.parent_id IS 'Идентификатор родительской компании';
COMMENT ON COLUMN company.name IS 'Наименование';
COMMENT ON COLUMN company.egrul_name IS 'Полное наименование компании в ЕГРЮЛ';
COMMENT ON COLUMN company.egrul_short_name IS 'Краткое наименование компании в ЕГРЮЛ';
COMMENT ON COLUMN company.inn IS 'ИНН';
COMMENT ON COLUMN company.dzo_percent IS 'Процент ДЗО';
COMMENT ON COLUMN company.can_sell_product IS 'Признак возможности продавать продукты';