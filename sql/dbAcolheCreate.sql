-- CREATE DATABASE dbAcolhe

DROP TABLE IF EXISTS MissaoCompleta;
DROP TABLE IF EXISTS Humor;
DROP TABLE IF EXISTS CompraSkin;
DROP TABLE IF EXISTS UsuarioClinica;
DROP TABLE IF EXISTS Clinica;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Missao;
DROP TABLE IF EXISTS Skin;


CREATE TABLE Skin
( codSkin   SERIAL 
, nmSkin    VARCHAR(50)
, descricao VARCHAR(80)
, imagem    TEXT
, valor     INT         DEFAULT(0)
, PRIMARY KEY (codSkin)
);


CREATE TABLE Missao
( codMissao SERIAL
, nmMissao  VARCHAR(50)
, descricao VARCHAR(80)
, valor     INT         DEFAULT(0)
, PRIMARY KEY (codMissao)
);


CREATE TABLE Usuario
( codUsuario       SERIAL
, nmUsuario        VARCHAR(50)
, saldo            INT
, diasConsecutivos INT
, imagem           TEXT
, telefone         BIGINT
, email            VARCHAR(50)
, senha            VARCHAR(50)
, codSkinPrincipal INT 
, dataCadastro     TIMESTAMP   DEFAULT(now())
, premium          BOOL        DEFAULT(FALSE)
, PRIMARY KEY (codUsuario)
, FOREIGN KEY (codSkinPrincipal) REFERENCES Skin (codSkin)
);


CREATE TABLE Clinica
( codClinica  SERIAL
, nmClinica   VARCHAR(50)
, descricao   VARCHAR(300)
, imagem      TEXT
, bairro      VARCHAR(50)
, cidade      VARCHAR(50)
, nmEstado    VARCHAR(50)
, sgEstado    VARCHAR(2)
, patrocinada BOOL        DEFAULT (FALSE)
, PRIMARY KEY (codClinica)
);


CREATE TABLE UsuarioClinica
( codClinica      INT
, codUsuario      INT 
, nivelSatisfacao SMALLINT 
, comentario      VARCHAR(300)
, PRIMARY KEY (codClinica, codUsuario)
, FOREIGN KEY (codClinica) REFERENCES Clinica (codClinica)
, FOREIGN KEY (codUsuario) REFERENCES Usuario (codUsuario)
);


CREATE TABLE CompraSkin
( codSkin    INT
, codUsuario INT
, valor      INT DEFAULT(0)
, PRIMARY KEY (codSkin, codUsuario)
, FOREIGN KEY (codSkin)    REFERENCES Skin    (codSkin)
, FOREIGN KEY (codUsuario) REFERENCES Usuario (codUsuario)
);


CREATE TABLE Humor
( codUsuario      INT
, data            DATE
, nivelSatisfacao SMALLINT
, descricao       VARCHAR(300)
, PRIMARY KEY (codUsuario, data)
, FOREIGN KEY (codUsuario) REFERENCES Usuario (codUsuario)
);


CREATE TABLE MissaoCompleta
( codMissao  INT
, codUsuario INT
, valor      INT DEFAULT(0)
, PRIMARY KEY (codMissao, codUsuario)
, FOREIGN KEY (codMissao)  REFERENCES Missao  (codMissao)
, FOREIGN KEY (codUsuario) REFERENCES Usuario (codUsuario)
);

