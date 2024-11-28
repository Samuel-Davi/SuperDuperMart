
create database superdupermart;
use superdupermart;

create table admin(
    username varchar(20) not null unique primary key,
    password varchar(15) not null,
    papel enum('adm', 'funcionario') default 'funcionario',
    ativo boolean default false
);

insert into admin values("samlima", "0907", 'adm', false);

create table marcas(
	nome varchar(45) not null unique primary key
);

create table produtos(
	id int not null unique primary key auto_increment,
    nome varchar(45) not null unique,
    nome_marca varchar(45),
    preco_compra decimal(10,2) not null,
    preco_venda decimal(10,2) not null,
    estoque_atual int unsigned,
    data_cadastro datetime default current_timestamp,
    foreign key (nome_marca) references marcas(nome)
);

#select * from produtos;

create table compras(
	id int not null primary key auto_increment unique,
    id_produto int not null,
	quantidade int unsigned,
    preco_unitario decimal(10,2) unsigned,
    data_compra datetime default current_timestamp,
    valor_total decimal(10,2) unsigned,
    foreign key (id_produto) references produtos(id)
);

#select * from compras;

create table vendas(
	id int not null auto_increment primary key,
    data_venda datetime default current_timestamp,
    valor_total decimal(10,2) unsigned,
    id_produto int,
    valor_pago decimal(10,2) unsigned,
    troco decimal(10,2),
    quantidade int unsigned,
    forma_pagamento varchar(45),
    foreign key (id_produto) references produtos(id)
);

#select * from vendas;