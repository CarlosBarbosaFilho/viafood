############################### CADASTRO DAS COZINHAS #####################################################

insert into viafood.tb_cozinhas (id,nome_cozinha) values (1,'Brasileira');
insert into viafood.tb_cozinhas(id,nome_cozinha) values (2,'Japonesa');
insert into viafood.tb_cozinhas (id,nome_cozinha) values (3,'Tailandesa');
insert into viafood.tb_cozinhas (id,nome_cozinha) values (4,'Italiana');

############################## CADSTRO DOS RESTAURANTES ####################################################

insert into viafood.tb_restaurantes (id,nome_restaurante, taxa_frete,cozinha_id) values (1,'Brasileirinho', 14.90,1);
insert into viafood.tb_restaurantes (id,nome_restaurante, taxa_frete,cozinha_id) values (2,'Saipan', 23.30,2);
insert into viafood.tb_restaurantes (id,nome_restaurante, taxa_frete,cozinha_id) values (3,'KingBox', 10.90, 3);
insert into viafood.tb_restaurantes (id,nome_restaurante, taxa_frete,cozinha_id) values (4,'Mamamia', 12.10, 4);

###############################  CADASTRO DAS FORMAS DE PAGAMENTO #############################################

insert into viafood.tb_formas_pagamentos (id,descricao_pagamento) values (1,'À vista');
insert into viafood.tb_formas_pagamentos (id,descricao_pagamento) values (2,'Débito');
insert into viafood.tb_formas_pagamentos (id,descricao_pagamento) values (3,'Crétido');
insert into viafood.tb_formas_pagamentos (id,descricao_pagamento) values (4,'Boleto');
insert into viafood.tb_formas_pagamentos (id,descricao_pagamento) values (5,'Transferência Bancária');

################################  CADASTRO DAS PERMISSÕES #####################################################

insert into viafood.tb_permissoes (id,nome_permissao,descricao_permissao) values (1,'Usuário','Permite apenas o acesso de usuários comuns.');
insert into viafood.tb_permissoes (id,nome_permissao,descricao_permissao) values (2,'Visitante','Permite apenas o acesso de visitantes.');
insert into viafood.tb_permissoes (id,nome_permissao,descricao_permissao) values (3,'Administrador','Permite o acesso ao administrador do sistema.');
insert into viafood.tb_permissoes (id,nome_permissao,descricao_permissao) values (4,'Master', 'Permite acesso total a todo o sistema.');

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
