-- CREATE DATABASE dbAcolhe

DROP TABLE IF EXISTS Humor;
DROP TABLE IF EXISTS CompraSkin;
DROP TABLE IF EXISTS UsuarioClinica;
DROP TABLE IF EXISTS Clinica;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Skin;


CREATE TABLE Skin
( codSkin   SERIAL 
, nmSkin    VARCHAR(50)
, descricao VARCHAR(80)
, imagem    TEXT
, valor     INT         DEFAULT(0)
, PRIMARY KEY (codSkin)
);


CREATE TABLE Usuario
( codUsuario       SERIAL
, nmUsuario        VARCHAR(50)
, saldo            INT
, diasConsecutivos INT
, email            VARCHAR(50)
, senha            VARCHAR(50)
, codSkinPrincipal INT 
, dataCadastro     TIMESTAMP   DEFAULT(now())
, dataUltimoAcesso DATE        DEFAULT(now())
, premium          BOOL        DEFAULT(FALSE)
, PRIMARY KEY (codUsuario)
, FOREIGN KEY (codSkinPrincipal) REFERENCES Skin (codSkin)
, UNIQUE (email)
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
( codUsuarioClinica SERIAL
, codClinica        INT
, codUsuario        INT 
, nivelSatisfacao   SMALLINT 
, comentario        VARCHAR(300)
, dataAvaliacao     TIMESTAMP    DEFAULT(now())
, PRIMARY KEY (codUsuarioClinica)
, FOREIGN KEY (codClinica) REFERENCES Clinica (codClinica)
, FOREIGN KEY (codUsuario) REFERENCES Usuario (codUsuario)
);


CREATE TABLE CompraSkin
( codCompraSkin SERIAL
, codSkin       INT
, codUsuario    INT
, valor         INT DEFAULT(0)
, PRIMARY KEY (codCompraSkin)
, FOREIGN KEY (codSkin)    REFERENCES Skin    (codSkin)
, FOREIGN KEY (codUsuario) REFERENCES Usuario (codUsuario)
, UNIQUE (codSkin, codUsuario)
);


CREATE TABLE Humor
( codHumor        SERIAL
, codUsuario      INT
, dataAvaliacao   DATE           DEFAULT(now())
, nivelSatisfacao SMALLINT
, comentario      VARCHAR(300)
, PRIMARY KEY (codHumor)
, FOREIGN KEY (codUsuario) REFERENCES Usuario (codUsuario)
);

