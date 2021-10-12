create table movimentacao (
	id bigint not null auto_increment,
	sinuca_id bigint not null,
	data_movimentacao datetime not null,
	fichas bigint not null,
    desconto_fichas bigint not null,
    total_fichas_empresa bigint not null,
    total_fichas_cliente bigint not null,
    diferenca_Fichas bigint not null,
    total_final_fichas bigint not null,
    
    primary key (id)
);

alter table movimentacao add constraint fk_movimentacao_sinuca
foreign key (sinuca_id) references sinuca (id);