-- Création du schema customer
CREATE SCHEMA IF NOT EXISTS customer;

-- Création de la séquence globale
CREATE SEQUENCE IF NOT EXISTS customer.GLOBAL_SEQUENCE
    START 1
    INCREMENT 1;

-- Création de la table customer
CREATE TABLE IF NOT EXISTS customer.customer (
    id            BIGINT DEFAULT nextval('customer.GLOBAL_SEQUENCE') PRIMARY KEY,
    prenom        VARCHAR(100),
    nom           VARCHAR(100)        NOT NULL,
    adresse       VARCHAR(255),
    payment_type  VARCHAR(50),
    created_date  TIMESTAMP,
    updated_date  TIMESTAMP
);