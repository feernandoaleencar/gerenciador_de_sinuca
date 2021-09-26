create table sinuca (
	id bigint not null auto_increment,
	cliente_id bigint not null,
	data_abertura datetime not null,
	data_fechamento datetime,
	contador_ficha bigint not null,
	vlr_ficha decimal(10,2) not null,
	porcentagem_empresa decimal(10,2) not null,
	porcentagem_cliente decimal(10,2) not null,
	patrimonio bigint not null,
    status varchar(20) not null,
    
    primary key (id)
);

alter table sinuca add constraint fk_sinuca_cliente
foreign key (cliente_id) references cliente (id);

