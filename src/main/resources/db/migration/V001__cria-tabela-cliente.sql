create table cliente (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    email varchar(255) not null,
    telefone varchar(20) not null,
    cpf varchar(14) not null,
	endereco varchar(60) not null,
	cidade varchar(20) not null,
	
    primary key (id)
);