ALTER TABLE `movimentacao` 
ADD COLUMN `vlr_total_fichas` DECIMAL(10,2) NULL AFTER `total_final_fichas`,
ADD COLUMN `vlr_total_fichas_empresa` DECIMAL(10,2) NULL AFTER `vlr_total_fichas`,
ADD COLUMN `vlr_total_fichas_cliente` DECIMAL(10,2) NULL AFTER `vlr_total_fichas_empresa`,
ADD COLUMN `vlr_total_fichas_desconto` DECIMAL(10,2) NULL AFTER `vlr_total_fichas_cliente`;
