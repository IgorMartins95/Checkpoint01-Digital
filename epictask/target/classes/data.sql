CREATE TABLE task (
	id int PRIMARY KEY auto_increment,
	title varchar(200),
	description TEXT,
	points int
);

INSERT INTO task VALUES(1, 'Criar Banco de dados', 'Criar o banco de dados na nuvem', 50);
INSERT INTO task VALUES(2, 'Criar prototipo', 'Criar o prototipo na nuvem', 50);
INSERT INTO task VALUES(3, 'Criar API REST dos dados', 'Criar o front end da aplicação', 50);