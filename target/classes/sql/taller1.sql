CREATE SCHEMA taller1;
USE taller1;

CREATE TABLE empleado(
documento VARCHAR(15) PRIMARY KEY NOT NULL,
nombres VARCHAR(40),
apellidos VARCHAR(40),
cargo VARCHAR(40),
salario INT
);
