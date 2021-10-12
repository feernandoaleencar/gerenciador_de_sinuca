ALTER TABLE movimentacao
CHANGE COLUMN total_fichas_empresa total_fichas_empresa BIGINT NULL ,
CHANGE COLUMN total_fichas_cliente total_fichas_cliente BIGINT NULL ,
CHANGE COLUMN diferenca_Fichas diferenca_Fichas BIGINT NULL ,
CHANGE COLUMN total_final_fichas total_final_fichas BIGINT NULL ;