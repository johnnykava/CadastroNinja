-- V2: Migrations para adicionar a coluna rank na tabela tb_ninja

ALTER TABLE tb_ninja
ADD COLUMN rank VARCHAR(255);