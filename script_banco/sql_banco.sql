CREATE SCHEMA ecommerce

CREATE TABLE ecommerce.enderecos(
	id serial PRIMARY KEY,
	cep CHARACTER VARYING(8) NOT NULL,
	logradouro CHARACTER VARYING NOT NULL,
	bairro CHARACTER VARYING NOT NULL,
	cidade CHARACTER VARYING NOT NULL,
	uf CHARACTER VARYING(2) NOT NULL,
	numero CHARACTER VARYING NOT NULL,
	complemento CHARACTER VARYING,
	ponto_referencia CHARACTER VARYING,
	data_hora_cadastro timestamp DEFAULT now(),
	data_hora_ultima_alteracao timestamp
);

CREATE TABLE ecommerce.imagens(
	id serial PRIMARY KEY,
	nome CHARACTER VARYING NOT NULL,
	imagem bytea NOT NULL,
	extensao_imagem CHARACTER VARYING NOT NULL,
	data_hora_cadastro timestamp DEFAULT now(),
	data_hora_ultima_alteracao timestamp
);

CREATE TABLE ecommerce.usuarios(
	id serial PRIMARY KEY,
	nome CHARACTER VARYING NOT NULL,
	cpf CHARACTER VARYING(11) NOT NULL,
	data_nascimento date NOT NULL,
	ddd CHARACTER VARYING(2) NOT NULL,
	telefone CHARACTER VARYING(10) NOT NULL,
	email CHARACTER VARYING(100) NOT NULL,
	senha CHARACTER VARYING NOT NULL,
	restrito boolean DEFAULT FALSE,
	endereco_id integer NOT NULL,
	imagem_id integer NOT NULL,
	data_hora_cadastro timestamp DEFAULT now(),
	data_hora_ultima_alteracao timestamp,
	FOREIGN KEY (endereco_id) references ecommerce.enderecos(id),
	FOREIGN KEY (imagem_id) references ecommerce.imagens(id)
);