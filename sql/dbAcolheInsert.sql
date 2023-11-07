DELETE FROM Humor;
DELETE FROM UsuarioClinica;
DELETE FROM Clinica;
DELETE FROM Usuario;

INSERT INTO Usuario (nmUsuario, email, senha)
    VALUES ('Andre Asseituno Mendes de Oliveira', 'andre.mendes@gmail.com', 'a12')
	     , ('Bruno Tadeu Pastrello Correa', 'bruno.correa@gmail.com', 'a12')
		 , ('Rafael Ferraz de Santana', 'rafael.santana@gmail.com', 'a12')
		 , ('Matheus Pedrosa Mendes da Costa', 'matheus.costa@gmail.com', 'a12')
		 , ('usuario2023', 'user@gmail.com', '123');

INSERT INTO Clinica (nmClinica, email, telefone, descricao, bairro, cidade, nmEstado, sgEstado, nivelSatisfacao)
     VALUES ('Aquarela de Emoções', 'aquarelasupport@gmail.com', '(11) 99405-5399', 'Consultório de psicologia especializado no acompanhamento psicoterapêutico de crianças e adolescentes.', 'Bela Vista', 'Osasco', 'São Paulo', 'SP', 5)
	      , ('Clinica Psicológica de Emoções', 'clinica@gmail.com', '(11) 95411-0128', NULL, 'Pirituba', 'São Paulo', 'São Paulo', 'SP', 3);

DO $$
DECLARE
    coduser1 INT := (SELECT codUsuario FROM Usuario LIMIT 1 OFFSET 0);
    coduser2 INT := (SELECT codUsuario FROM Usuario LIMIT 1 OFFSET 1);
    coduser3 INT := (SELECT codUsuario FROM Usuario LIMIT 1 OFFSET 2);
    coduser4 INT := (SELECT codUsuario FROM Usuario LIMIT 1 OFFSET 3);
    coduser5 INT := (SELECT codUsuario FROM Usuario LIMIT 1 OFFSET 4);
BEGIN

INSERT INTO UsuarioClinica (codClinica
						  , codUsuario
						  , nivelSatisfacao
						  , comentario)
     VALUES ((SELECT codClinica FROM Clinica LIMIT 1 OFFSET 0), coduser1, 5, 'Adorei essa Clínca')
 	      , ((SELECT codClinica FROM Clinica LIMIT 1 OFFSET 0), coduser2, 4, 'Gostei muito da Clínca')
 	      , ((SELECT codClinica FROM Clinica LIMIT 1 OFFSET 0), coduser3, 3, 'Achei a Clínica razoável')		
 		  , ((SELECT codClinica FROM Clinica LIMIT 1 OFFSET 0), coduser4, 2, 'Ruim essa Clínca')	
 		  , ((SELECT codClinica FROM Clinica LIMIT 1 OFFSET 0), coduser5, 1, 'Odiei essa Clínica')	
		  
		  , ((SELECT codClinica FROM Clinica LIMIT 1 OFFSET 1), coduser5, 1, 'Nunca Ouvi Falar dessa Clínica')
		  , ((SELECT codClinica FROM Clinica LIMIT 1 OFFSET 1), coduser4, 2, 'Nunca fui, mas poderia melhorar')
		  , ((SELECT codClinica FROM Clinica LIMIT 1 OFFSET 1), coduser3, 3, 'Nem fede nem cheira')
		  , ((SELECT codClinica FROM Clinica LIMIT 1 OFFSET 1), coduser2, 4, 'Parabéns à Clínica, faz o mínimo')
		  , ((SELECT codClinica FROM Clinica LIMIT 1 OFFSET 1), coduser1, 5, 'Top');
		  
		  
INSERT INTO Humor (codUsuario, dataAvaliacao, nivelSatisfacao, comentario)
     VALUES (coduser1, '20231003', 5, 'Me diverti nesse dia!')
	      , (coduser1, '20231002', 3, NULL)
		  , (coduser1, '20230901', 1, 'Não me diverti nesse dia!')
		  
		  , (coduser2, '20230904', 4, 'Não entendi')
		  , (coduser2, '20231005', 3, 'hmmm')
		  , (coduser2, '20231006', 4, 'Legal')
		  
		  , (coduser3, '20230907', 1, 'oieee')
		  , (coduser3, '20231008', 2, 'Meh')
		  , (coduser3, '20231009', 1, 'upa!')
		  
		  , (coduser4, '20231010', 4, NULL)
		  , (coduser4, '20231011', 5, 'Me diverti nesse dia!')
		  , (coduser4, '20230904', 5, 'Me diverti nesse dia!')
		  
		  , (coduser5, '20230904', 3, 'Me diverti nesse dia!')
		  , (coduser5, '20231004', 3, NULL)
		  , (coduser2, '20231004', 3, 'Me diverti nesse dia!');
		  
END $$;
		  