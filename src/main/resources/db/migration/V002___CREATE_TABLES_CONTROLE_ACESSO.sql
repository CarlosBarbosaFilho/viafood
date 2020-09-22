####### CRIANDO A TABELA DAS PERMISSÕES ################
create table tb_permissoes (
	id bigint not null auto_increment,
	nome_permissao varchar (50) not null,
	descricao_permissao varchar (255) not null,
	
	primary key (id)
);


####### CRIANDO A TABELA DOS GRUPOS ######################
create table tb_grupos (
	id bigint not null auto_increment,
	nome_grupo varchar (50) not null,
	
	primary key (id)
);

####### CRIANDO A TABELA GRUPOS PERMISSÕES ################
create table tb_grupos_permissoes (
	id_permissao bigint not null,
	id_grupo bigint not null,
	
	primary key (id_grupo, id_permissao)
);

####### CRIANDO A TABELA DOS USUARIOS ######################
create table tb_usuarios (
	id bigint not null auto_increment,
	nome_usuario varchar (200) not  null,
	email_usuario varchar (200) not null,
	senha_usuario varchar (20) not null,
	data_cadastro datetime not null,
	
	primary key (id)
);

####### CRIANDO A TABELA USUARIOS GRUPOS #####################
create table tb_usuarios_grupos (
	id_usuario bigint not null,
	id_grupo bigint not null,
	
	primary key (id_usuario, id_grupo)
);
