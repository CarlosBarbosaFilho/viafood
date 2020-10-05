set foreign_key_checks = 0;

delete from viafood.tb_cidades;
delete from viafood.tb_cozinhas;
delete from viafood.tb_estados;
delete from viafood.tb_formas_pagamentos;
delete from viafood.tb_restaurantes;
delete from viafood.tb_restaurantes_formas_pagamentos;
delete from viafood.tb_produtos;
delete from viafood.tb_pedidos;
delete from viafood.tb_itens_pedidos;
delete from viafood.tb_permissoes;
delete from viafood.tb_grupos;
delete from viafood.tb_grupos_permissoes;
delete from viafood.tb_usuarios;
delete from viafood.tb_usuarios_grupos;


set foreign_key_checks = 1;

alter table viafood.tb_cidades auto_increment = 1;
alter table viafood.tb_cozinhas auto_increment = 1;
alter table viafood.tb_estados auto_increment = 1;
alter table viafood.tb_formas_pagamentos auto_increment = 1;
alter table viafood.tb_restaurantes auto_increment = 1;
alter table viafood.tb_produtos auto_increment = 1;
alter table viafood.tb_pedidos auto_increment = 1;
alter table viafood.tb_itens_pedidos auto_increment = 1;
alter table viafood.tb_permissoes auto_increment = 1;
alter table viafood.tb_grupos auto_increment = 1;
alter table viafood.tb_usuarios auto_increment = 1;

#################################   CADASTRO DOS ESTADOS #########################################################
INSERT INTO viafood.tb_estados (id,nome) values (1,'Paraíba');
INSERT INTO viafood.tb_estados (id,nome) values (2,'São Paulo');
INSERT INTO viafood.tb_estados (id,nome) values (3,'Rio Grande do Norte');
INSERT INTO viafood.tb_estados (id,nome) values (4,'Pernambuco');

################################ CADASTRO DAS CIDADES #############################################################
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (1, 'João Pessoa',1);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (2, 'Junco do Seridó',1);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (3, 'Campima Grande',1);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (4, 'Assunção',1);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (5, 'Jundiaí',2);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (6, 'São Paulo',2);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (7, 'São Bernardo do Campo',2);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (8, 'Equador',3);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (9, 'Parelhas', 3);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (10, 'Caicó',3);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (11, 'Itapetim',4);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (12, 'Recife',4);
INSERT INTO viafood.tb_cidades (id,nome, estado_id) values (13, 'Arco Verde',4);


###############################  CADASTRO DAS FORMAS DE PAGAMENTO #####################################################
INSERT INTO viafood.tb_formas_pagamentos (id,descricao,data_cadastro,data_atualizacao) values (1,'À vista',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_formas_pagamentos (id,descricao,data_cadastro,data_atualizacao) values (2,'Débito',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_formas_pagamentos (id,descricao,data_cadastro,data_atualizacao) values (3,'Crétido',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_formas_pagamentos (id,descricao,data_cadastro,data_atualizacao) values (4,'Boleto',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_formas_pagamentos (id,descricao,data_cadastro,data_atualizacao) values (5,'Transferência Bancária',utc_timestamp, utc_timestamp);

############################ CADASTRO DAS COZINHAS ############################################################
INSERT INTO viafood.tb_cozinhas (id,nome) values (1,'Brasileira');
INSERT INTO viafood.tb_cozinhas(id,nome) values (2,'Japonesa');
INSERT INTO viafood.tb_cozinhas (id,nome) values (3,'Tailandesa');
INSERT INTO viafood.tb_cozinhas (id,nome) values (4,'Italiana');

############################## CADSTRO DOS RESTAURANTES ##############################################################
INSERT INTO viafood.tb_restaurantes (id,nome, taxa_frete,cozinha_id,data_cadastro,data_atualizacao,endereco_cidade_id,endereco_cep,endereco_logradouro,endereco_numero,endereco_bairro, ativo) values (1, 'Brasileirinho', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro',1);
INSERT INTO viafood.tb_restaurantes (id,nome, taxa_frete,cozinha_id,data_cadastro,data_atualizacao,endereco_cidade_id,endereco_cep,endereco_logradouro,endereco_numero,endereco_bairro, ativo) values (2,'Saipan', 23.30,2,utc_timestamp, utc_timestamp,1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro',1);
INSERT INTO viafood.tb_restaurantes (id,nome, taxa_frete,cozinha_id,data_cadastro,data_atualizacao,endereco_cidade_id,endereco_cep,endereco_logradouro,endereco_numero,endereco_bairro, ativo) values (3,'KingBox', 10.90, 3,utc_timestamp, utc_timestamp,1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro',1);
INSERT INTO viafood.tb_restaurantes (id,nome, taxa_frete,cozinha_id,data_cadastro,data_atualizacao,endereco_cidade_id,endereco_cep,endereco_logradouro,endereco_numero,endereco_bairro, ativo) values (4,'Mamamia', 12.10, 4,utc_timestamp, utc_timestamp,1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro',1);

######################################################### RESTAURANTES_PAGAMENTOS ########################################
INSERT INTO tb_restaurantes_formas_pagamentos (restaurante_id, forma_pagamento_id ) values (1,1),(1,2),(1,3),(2,3),(3,2),(3,3);

######################################################### PRODUTOS #######################################################
INSERT INTO viafood.tb_produtos (id,nome,descricao, preco, ativo,data_cadastro,data_atualizacao, restaurante_id) values (1,'Filé Parmegiana', 'Filé mingnhon com queijo', 130, true,utc_timestamp, utc_timestamp, 1);
INSERT INTO viafood.tb_produtos (id,nome,descricao, preco, ativo,data_cadastro,data_atualizacao, restaurante_id) values (2,'Bife à Cavala', 'Bife de maminha com ovos grelhados', 120, true, utc_timestamp, utc_timestamp,1);
INSERT INTO viafood.tb_produtos (id,nome,descricao, preco, ativo,data_cadastro,data_atualizacao, restaurante_id) values (3,'Filé com frita', 'Filé com batas fritas', 70, true, utc_timestamp, utc_timestamp, 2);
INSERT INTO viafood.tb_produtos (id,nome,descricao, preco, ativo,data_cadastro,data_atualizacao, restaurante_id) values (4,'Macarronada do chef', 'Macarrão com molho de tomates especial', 80, true, utc_timestamp, utc_timestamp, 3);
INSERT INTO viafood.tb_produtos (id,nome,descricao, preco, ativo,data_cadastro,data_atualizacao, restaurante_id) values (5,'Cordeiro Paraíba', 'Filé de cordeiro marinado no azeite e vinho do porto', 230, true, utc_timestamp, utc_timestamp, 4);
INSERT INTO viafood.tb_produtos (id,nome,descricao, preco, ativo,data_cadastro,data_atualizacao, restaurante_id) values (6,'Carne de sol na nata', 'Carne de sol desfiada na nata e cebolas brancas', 90, true, utc_timestamp, utc_timestamp, 4);

################################  CADASTRO DAS PERMISSÕES #############################################################
INSERT INTO viafood.tb_permissoes (id,nome,descricao,data_cadastro,data_atualizacao) values (1,'Usuário','Permite apenas o acesso de usuários comuns.',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_permissoes (id,nome,descricao,data_cadastro,data_atualizacao) values (2,'Visitante','Permite apenas o acesso de visitantes.',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_permissoes (id,nome,descricao,data_cadastro,data_atualizacao) values (3,'Administrador','Permite o acesso ao administrador do sistema.',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_permissoes (id,nome,descricao,data_cadastro,data_atualizacao) values (4,'Master', 'Permite acesso total a todo o sistema.',utc_timestamp, utc_timestamp);

################################  CADASTRO DAS GRUPOS ###################################################################
INSERT INTO viafood.tb_grupos (id, descricao_grupo,data_cadastro,data_atualizacao) values (1, 'Administradores',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_grupos (id, descricao_grupo,data_cadastro,data_atualizacao) values (2, 'Gerentes ',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_grupos (id, descricao_grupo,data_cadastro,data_atualizacao) values (3, 'Comercial',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_grupos (id, descricao_grupo,data_cadastro,data_atualizacao) values (4, 'Suporte Técnico',utc_timestamp, utc_timestamp);

######################################################### USUARIOS #######################################################
INSERT INTO viafood.tb_usuarios (id, nome, email, senha,data_cadastro,data_atualizacao) values (1, 'Carlos Barbosa G. Filho', 'cbarbosagomesfilho@gmail.com', '123mudar',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_usuarios (id, nome, email, senha,data_cadastro,data_atualizacao) values (2, 'Barbara de Assis Xavier', 'barbara.a.xavier@gmail.com', '123mudar',utc_timestamp, utc_timestamp);
INSERT INTO viafood.tb_usuarios (id, nome, email, senha,data_cadastro,data_atualizacao) values (3, 'Maria Alice Barbosa Xavier', 'maria.a.xavier@gmail.com', '123mudar',utc_timestamp, utc_timestamp);

######################################################### GRUPOS_PERMISSOES ###############################################
INSERT INTO tb_grupos_permissoes (id_grupo ,id_permissao ) values (1,1),(1,2),(1,3),(1,4),(2,2),(2,3),(3,2),(4,1);

######################################################### USUARIOS_GRUPOS #################################################
INSERT INTO tb_usuarios_grupos  (id_usuario , id_grupo ) values (1,1),(1,2),(1,3),(2,2),(3,2),(3,1);




