CREATE DATABASE lojaDB;
USE lojaDB;

CREATE USER 'app_user'@'localhost' IDENTIFIED BY 'appuser123';
GRANT SELECT, INSERT, UPDATE, DELETE ON lojaDB.* TO 'app_user'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE clientes (
    id INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pedidos (
    numero_controle INT NOT NULL,
    data_cadastro DATE DEFAULT NULL,
    nome VARCHAR(100) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    quantidade INT DEFAULT NULL,
    codigo_cliente INT NOT NULL,
    PRIMARY KEY (numero_controle)
);

ALTER TABLE pedidos
ADD CONSTRAINT fk_codigo_cliente
FOREIGN KEY (codigo_cliente) REFERENCES clientes(id);


INSERT INTO clientes (id, nome, cpf) VALUES
(1, 'Cliente 1', '12345678901'),
(2, 'Cliente 2', '23456789012'),
(3, 'Cliente 3', '34567890123'),
(4, 'Cliente 4', '45678901234'),
(5, 'Cliente 5', '56789012345'),
(6, 'Cliente 6', '67890123456'),
(7, 'Cliente 7', '78901234567'),
(8, 'Cliente 8', '89012345678'),
(9, 'Cliente 9', '90123456789'),
(10, 'Cliente 10', '01234567890');

