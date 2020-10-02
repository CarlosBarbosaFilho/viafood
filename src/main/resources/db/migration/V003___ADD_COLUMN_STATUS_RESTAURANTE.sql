alter table tb_restaurantes add ativo tinyint(1) not null;
update tb_restaurantes set ativo = true;
