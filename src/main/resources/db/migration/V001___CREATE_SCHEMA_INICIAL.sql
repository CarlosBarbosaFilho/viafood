####### CRIANDO A TABELA DOS ESTADOS ################
create table tb_estados (
	id bigint not null auto_increment,
	nome varchar (100) not null,
	
	primary key (id)
);

####### CRIANDO A TABELA DAS CIDADES ################
create table tb_cidades (
	id bigint not null auto_increment,
	nome varchar (100) not null,
	estado_id bigint not null,
	
	foreign key (estado_id) references tb_estados (id),
	
	primary key (id)
);

####### CRIANDO A TABELA DAS FORMAS DE PAGAMENTO ######
create table tb_formas_pagamentos (
	id bigint not null auto_increment,
	descricao varchar (100) not null,
	
	primary key (id)
);

####### CRIANDO A TABELA DAS COZINHAS ##################
create table tb_cozinhas (
	id bigint not null auto_increment,
	nome varchar (100) not null,
	
	primary key (id)
);

####### CRIANDO A TABELA DOS RESTAURANTES ################
create table tb_restaurantes (
	id bigint not null auto_increment,
	taxa_frete decimal (10,2) not null,
	nome varchar (100) not null,
	
	endereco_cep varchar (30),
	endereco_logradouro varchar (100),
	endereco_numero varchar (10),
	endereco_complemento varchar (200),
	endereco_bairro varchar (30),
	endereco_cidade_id bigint,
	
	cozinha_id bigint not null,
	data_cadastro datetime not null,
	data_atualizacao datetime not null,
	
	foreign key (cozinha_id) references tb_cozinhas (id),
	foreign key (endereco_cidade_id) references tb_cidades (id),
	
	primary key (id)
);

####### CRIANDO A TABELA RESTAURANTES_FORMAS_PAGAMENTO ######
create table tb_restaurantes_formas_pagamentos (
	restaurante_id bigint not null,
	forma_pagamento_id bigint not null,
	
	foreign key (restaurante_id) references tb_restaurantes (id),
	foreign key (forma_pagamento_id) references tb_formas_pagamentos (id),
	
	primary key (restaurante_id,forma_pagamento_id)
);

####### CRIANDO A TABELA DOS PRODUTPS ###########################
create table tb_produtos (
	id bigint not null auto_increment,
	nome varchar (100) not null,
	descricao varchar (255) not null,
	preco decimal (10,2) not null,
	ativo tinyint(1) not null,
	restaurante_id bigint not null,
	
	foreign key (restaurante_id) references tb_restaurantes (id),
	
	primary key (id)
);

####### CRIANDO A TABELA DOS PEDIDOS ############################
create table tb_pedidos (
	id bigint not null auto_increment,
	subtotal decimal not null,
	taxa_frete decimal not null,
	valor_total decimal not null,
	data_cadastro datetime not null,
	data_confirmacao datetime not null,
	data_cancelamentoo datetime,
	data_entrega_pedido datetime,
	
	endereco_cep varchar (30),
	endereco_logradouro varchar (200),
	endereco_numero varchar (10),
	endereco_complemento varchar (200),
	endereco_bairro varchar (30),
	endereco_cidade_id bigint,
	
	restaurante_id bigint not null,
	forma_pagamento_id bigint not null,
	usuario_cliente_id bigint not null,
	
	foreign key (endereco_cidade_id) references tb_cidades (id),
	foreign key (restaurante_id) references tb_restaurantes (id),
	foreign key (forma_pagamento_id) references tb_formas_pagamentos (id),

	primary key (id)
);

####### CRIANDO A TABELA DOS ITENS PEDIDOS ########################
create table tb_itens_pedidos (
	id bigint not null auto_increment,
	quantidade integer not null,
	preco_unitario  decimal not null,
	preco_total decimal not null,
	observacao varchar (255),
	pedido_id bigint not null,
	produto_id bigint not null,
	
    foreign key (pedido_id) references tb_pedidos (id),
    foreign key (produto_id) references tb_produtos (id),
	primary key (id)
);

