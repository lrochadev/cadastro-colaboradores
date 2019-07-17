DROP SCHEMA IF EXISTS desafio;

CREATE SCHEMA IF NOT EXISTS desafio;
USE desafio;


CREATE TABLE setor(
setr_id int not null AUTO_INCREMENT,
setr_descricao VARCHAR(255) NOT NULL,
constraint pk_setor PRIMARY KEY (setr_id)
);

CREATE TABLE colaborador(
cola_id int NOT NULL AUTO_INCREMENT,
setr_id int NOT NULL,
cola_nome VARCHAR(100) NOT NULL,
cola_cpf VARCHAR(20) NULL,
cola_telefone VARCHAR(20) NULL,
cola_email VARCHAR(80) NULL,
cola_dt_nascimento DATE NULL,
constraint pk_colaborador PRIMARY KEY (cola_id),
constraint fk_setor FOREIGN KEY (setr_id) REFERENCES setor(setr_id)
);

INSERT INTO setor(setr_id, setr_descricao) values (1, "Setor 1");
INSERT INTO setor(setr_id, setr_descricao) values (2, "Setor 2");
INSERT INTO setor(setr_id, setr_descricao) values (3, "Setor 3");

COMMIT;