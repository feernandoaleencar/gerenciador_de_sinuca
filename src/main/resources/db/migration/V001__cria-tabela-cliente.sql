create table cliente (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    email varchar(255) not null,
    cpf varchar(14) not null,
    logradouro VARCHAR(30),
    numero VARCHAR(30),
    complemento VARCHAR(30),
    bairro VARCHAR(30),
    cep VARCHAR(30),
    primary key (id)
);