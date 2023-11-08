DELETE FROM Humor;
DELETE FROM Clinica;
DELETE FROM Usuario;

INSERT INTO Usuario (nmUsuario, email, senha)
    VALUES ('Andre Asseituno Mendes de Oliveira', 'andre.mendes@gmail.com', 'a12')
	     , ('Bruno Tadeu Pastrello Correa', 'bruno.correa@gmail.com', 'a12')
		 , ('Rafael Ferraz de Santana', 'rafael.santana@gmail.com', 'a12')
		 , ('Matheus Pedrosa Mendes da Costa', 'matheus.costa@gmail.com', 'a12')
		 , ('usuario2023', 'user@gmail.com', '123');

INSERT INTO Clinica (nmClinica, email, telefone, descricao, imagem, bairro, cidade, nmEstado, sgEstado, patrocinada, nivelsatisfacao)
     VALUES ('Aquarela de Emoções', 'aquarelasupport@gmail.com', '(11) 99405-5399', 'Consultório de psicologia especializado no acompanhamento psicoterapêutico de crianças e adolescentes.', 'https://www.cloudia.com.br/wp-content/uploads/2020/08/hospital_infantil_3-1024x775.jpg', 'Bela Vista', 'Osasco', 'São Paulo', 'SP', true, 5)
	      , ('Clinica Psicológica de Emoções', 'administrativo314832@gmail.com', '(11) 95411-0128', 'Atendimentos a adolescentes, adultos, idosos, individual e casal.', 'https://www.amplimed.com.br/wp-content/uploads/2021/11/diferenca_consultorio_clinica_centro_blog.jpeg', 'Pirituba', 'São Paulo', 'São Paulo', 'SP', false, 3)
		  , ('CENTRO PSICOLÓGICO ESSÊNCIA VIVA', 'clinica.viva@essenciaviva.com', '11987654321', 'NO CENTRO PSICOLÓGICO ESSÊNCIA VIVA, ACREDITAMOS QUE CADA PESSOA TEM UMA ESSÊNCIA VIVA', 'https://goclinica.com.br/wp-content/uploads/2022/01/5-min.jpg', 'JARDINS', 'SÃO PAULO', 'São Paulo', 'SP', false, 4)
		  , ('CLÍNICA DA ALMA LIVRE', 'alma@livre.com', '11223344556', 'NA CLÍNICA DA ALMA LIVRE, BUSCAMOS LIBERAR A ALMA DE SUAS AMARRAS EMOCIONAIS. NOSSA EQUIPE DE PSICÓLOGOS ESTÁ PRONTA PARA AJU', 'https://www.psicologosberrini.com.br/wp-content/uploads/cropped-consultorio-psicologa-1-1024x576.jpg', 'FLORES DA PAZ', 'CAMPINAS', 'São Paulo', 'SP', true, 5);

DO $$
DECLARE
    coduser1 INT := (SELECT codUsuario FROM Usuario LIMIT 1 OFFSET 0);
    coduser2 INT := (SELECT codUsuario FROM Usuario LIMIT 1 OFFSET 1);
    coduser3 INT := (SELECT codUsuario FROM Usuario LIMIT 1 OFFSET 2);
    coduser4 INT := (SELECT codUsuario FROM Usuario LIMIT 1 OFFSET 3);
    coduser5 INT := (SELECT codUsuario FROM Usuario LIMIT 1 OFFSET 4);
BEGIN		  
		  
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
		  