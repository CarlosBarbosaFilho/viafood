############################### CADASTRO DAS COZINHAS ############################################################

insert into viafood.tb_cozinhas (id,nome_cozinha) values (1,'Brasileira');
insert into viafood.tb_cozinhas(id,nome_cozinha) values (2,'Japonesa');
insert into viafood.tb_cozinhas (id,nome_cozinha) values (3,'Tailandesa');
insert into viafood.tb_cozinhas (id,nome_cozinha) values (4,'Italiana');

#################################   CADASTRO DOS ESTADOS #########################################################

insert into viafood.tb_estados (id,nome_estado) values (1,'Paraíba');
insert into viafood.tb_estados (id,nome_estado) values (2,'São Paulo');
insert into viafood.tb_estados (id,nome_estado) values (3,'Rio Grande do Norte');
insert into viafood.tb_estados (id,nome_estado) values (4,'Pernambuco');

################################ CADASTRO DAS CIDADES #############################################################

insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (1, 'João Pessoa',1);
insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (2, 'Junco do Seridó',1);
insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (3, 'Campima Grande',1);
insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (4, 'Assunção',1);


insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (5, 'Jundiaí',2);
insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (6, 'São Paulo',2);
insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (7, 'São Bernardo do Campo',2);


insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (8, 'Equador',3);
insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (9, 'Parelhas',3);
insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (10, 'Caicó',3);


insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (11, 'Itapetim',4);
insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (12, 'Recife',4);
insert into viafood.tb_cidades (id,nome_cidade, estado_id) values (13, 'Arco Verde',4);


############################## CADSTRO DOS RESTAURANTES ##############################################################

insert into viafood.tb_restaurantes (id, nome_restaurante, taxa_frete, cozinha_id, endereco_cidade_id,data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Brasileirinho', 10, 1, 1, utc_timestamp,utc_timestamp, '58051840', 'Rua Adalgisa Luna de Menezes', '731', 'Bancários');
insert into viafood.tb_restaurantes (id,nome_restaurante, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values (2,'Saipan', 23.30,2,utc_timestamp, utc_timestamp );
insert into viafood.tb_restaurantes (id,nome_restaurante, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values (3,'KingBox', 10.90, 3,utc_timestamp, utc_timestamp);
insert into viafood.tb_restaurantes (id,nome_restaurante, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values (4,'Mamamia', 12.10, 4,utc_timestamp, utc_timestamp);


###############################  CADASTRO DAS FORMAS DE PAGAMENTO #####################################################

insert into viafood.tb_formas_pagamentos (id,descricao_pagamento) values (1,'À vista');
insert into viafood.tb_formas_pagamentos (id,descricao_pagamento) values (2,'Débito');
insert into viafood.tb_formas_pagamentos (id,descricao_pagamento) values (3,'Crétido');
insert into viafood.tb_formas_pagamentos (id,descricao_pagamento) values (4,'Boleto');
insert into viafood.tb_formas_pagamentos (id,descricao_pagamento) values (5,'Transferência Bancária');

################################  CADASTRO DAS PERMISSÕES #############################################################

insert into viafood.tb_permissoes (id,nome_permissao,descricao_permissao) values (1,'Usuário','Permite apenas o acesso de usuários comuns.');
insert into viafood.tb_permissoes (id,nome_permissao,descricao_permissao) values (2,'Visitante','Permite apenas o acesso de visitantes.');
insert into viafood.tb_permissoes (id,nome_permissao,descricao_permissao) values (3,'Administrador','Permite o acesso ao administrador do sistema.');
insert into viafood.tb_permissoes (id,nome_permissao,descricao_permissao) values (4,'Master', 'Permite acesso total a todo o sistema.');


################################  CADASTRO DAS GRUPOS ###################################################################

insert into viafood.tb_grupos (id, nome_grupo) values (1, 'Administradores');
insert into viafood.tb_grupos (id, nome_grupo) values (2, 'Gerentes ');
insert into viafood.tb_grupos (id, nome_grupo) values (3, 'Comercial');
insert into viafood.tb_grupos (id, nome_grupo) values (4, 'Suporte Técnico');

######################################################### RESTAURANTES_PAGAMENTOS ########################################

insert into tb_restautantes_formas_pagamentos (restaurante_id, forma_pagamento_id ) values (1,1),(1,2),(1,3),(2,3),(3,2),(3,3);

######################################################### PRODUTOS #######################################################

insert into viafood.tb_produtos (id,nome_produto,descricao_produto, preco_produto, ativo, restaurante_id) values (1,'Filé Parmegiana', 'Filé mingnhon com queijo', 130, true, 1);
insert into viafood.tb_produtos (id,nome_produto,descricao_produto, preco_produto, ativo, restaurante_id) values (2,'Bife à Cavala', 'Bife de maminha com ovos grelhados', 120, true, 1);
insert into viafood.tb_produtos (id,nome_produto,descricao_produto, preco_produto, ativo, restaurante_id) values (3,'Filé com frita', 'Filé com batas fritas', 70, true, 2);
insert into viafood.tb_produtos (id,nome_produto,descricao_produto, preco_produto, ativo, restaurante_id) values (4,'Macarronada do chef', 'Macarrão com molho de tomates especial', 80, true, 3);
insert into viafood.tb_produtos (id,nome_produto,descricao_produto, preco_produto, ativo, restaurante_id) values (5,'Cordeiro Paraíba', 'Filé de cordeiro marinado no azeite e vinho do porto', 230, true, 4);
insert into viafood.tb_produtos (id,nome_produto,descricao_produto, preco_produto, ativo, restaurante_id) values (6,'Carne de sol na nata', 'Carne de sol desfiada na nata e cebolas brancas', 90, true, 4);

######################################################### USUARIOS #######################################################

insert into viafood.tb_usuarios (id, nome_usuario, email_usuario, senha_usuario, data_cadastro) values (1, 'Carlos Barbosa G. Filho', 'cbarbosagomesfilho@gmail.com', '123mudar', utc_timestamp);
insert into viafood.tb_usuarios (id, nome_usuario, email_usuario, senha_usuario, data_cadastro) values (2, 'Barbara de Assis Xavier', 'barbara.a.xavier@gmail.com', '123mudar', utc_timestamp);
insert into viafood.tb_usuarios (id, nome_usuario, email_usuario, senha_usuario, data_cadastro) values (3, 'Maria Alice Barbosa Xavier', 'maria.a.xavier@gmail.com', '123mudar', utc_timestamp);


######################################################### GRUPOS_PERMISSOES ###############################################
insert into tb_grupos_permissoes (grupo_id ,permissao_id ) values (1,1),(1,2),(1,3),(1,4),(2,2),(2,3),(3,2),(4,1);

######################################################### USUARIOS_GRUPOS #################################################
insert into tb_usuarios_grupos  (usuario_id , grupo_id ) values (1,1),(1,2),(1,3),(2,2),(3,2),(3,1);











