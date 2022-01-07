DROP TABLE IF EXISTS person,doctype,organisations,orgtype,attachMO CASCADE;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE person (
    Id INT PRIMARY KEY,
    LastName VARCHAR(50),
    FirstName VARCHAR(50),
    Patronymic VARCHAR(50),
    DateBirth DATE,
    PlaceBirth VARCHAR(50),
    DocId INT,
    DocNumber VARCHAR(50),
    Phone VARCHAR(10),
    Email VARCHAR(30)
);
CREATE TABLE doctype (
    Id INT PRIMARY KEY,
    Type VARCHAR(20)
);
CREATE TABLE organisations (
    Id INT PRIMARY KEY,
    TypeOrg INT,
    Address VARCHAR(50),
    NameOrg VARCHAR(50),
    HeadOrg VARCHAR(50)
);
CREATE TABLE orgtype (
    Id INT PRIMARY KEY,
    Type VARCHAR(20)
);
CREATE TABLE attachmo
(
    Id        INT PRIMARY KEY,
    PersonId  INT,
    OrgId     INT,
    DateStart TIMESTAMP,
    DateEnd   TIMESTAMP
);
