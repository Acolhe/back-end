-- CREATE DATABASE dbAcolhe

DROP TABLE IF EXISTS Humor;
DROP TABLE IF EXISTS Clinica;
DROP TABLE IF EXISTS Usuario;


CREATE TABLE Usuario
( codUsuario       SERIAL
, nmUsuario        VARCHAR(50)
, saldo            INT         DEFAULT(0)
, diasConsecutivos INT         DEFAULT(0)
, email            VARCHAR(50) 
, senha            VARCHAR(50)
, codSkinPrincipal INT 
, dataCadastro     TIMESTAMP   DEFAULT(now())
, dataUltimoAcesso DATE        DEFAULT(now())
, premium          BOOL        DEFAULT(FALSE)
, PRIMARY KEY (codUsuario)
, UNIQUE (email)
);


CREATE TABLE Clinica
( codClinica       SERIAL
, nmClinica        VARCHAR(50)
, email            VARCHAR(50)
, telefone         VARCHAR(50)
, descricao        VARCHAR(125)
, imagem           TEXT
, bairro           VARCHAR(50)
, cidade           VARCHAR(50)
, nmEstado         VARCHAR(50)
, sgEstado         VARCHAR(2)
, patrocinada      BOOL         DEFAULT (FALSE)
, nivelSatisfacao  SMALLINT     DEFAULT (3)
, PRIMARY KEY (codClinica)
, UNIQUE (email)
, UNIQUE (telefone)
);


CREATE TABLE Humor
( codHumor        SERIAL
, codUsuario      INT
, dataAvaliacao   DATE         DEFAULT(now())
, nivelSatisfacao SMALLINT
, comentario      VARCHAR(125) 
, PRIMARY KEY (codHumor)
, FOREIGN KEY (codUsuario) REFERENCES Usuario (codUsuario)
);