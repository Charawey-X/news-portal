SET MODE postgres

CREATE DATABASE news_portal OWNER x;

\c news_portal

CREATE TABLE IF NOT EXISTS departments(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    description VARCHAR,
    numberOfEmployees INTEGER
)

CREATE TABLE IF NOT EXISTS news(
    id SERIAL PRIMARY KEY,
    content VARCHAR,
    author VARCHAR,
    departmentId INTEGER,
    type VARCHAR
)

CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    role VARCHAR,
    departmentId INTEGER
)
CREATE DATABASE news_portal_test WITH TEMPLATE news_portal OWNER x;