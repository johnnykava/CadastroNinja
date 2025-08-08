-- V3: Migrations para adicionar a coluna imagemUrl na tabela tb_ninja

ALTER TABLE tb_ninja
ADD COLUMN imagemUrl VARCHAR(255);
