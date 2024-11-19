show databases;
create database superdupermart;
use superdupermart;
show tables;

create table admin(
	id int not null auto_increment primary key,
    username varchar(20),
    password varchar(15)
);

insert into admin values(null, "samlima", "0907");
select * from admin;

create table produtos(
    nome varchar(45) not null unique primary key,
    preco_compra decimal(10,2) not null,
    preco_venda decimal(10,2) not null,
    estoque_atual int unsigned,
    data_cadastro datetime default current_timestamp
);

select * from produtos;

delete from produtos where nome = "frango empanado";

insert into produtos values ("arroz", 5.5, 7.15, 5, default);

create table fornecedores(
    nome varchar(45) not null primary key unique,
    contato varchar(45)
);

delete from fornecedores where nome = "perdigão";
select * from fornecedores;
insert into fornecedores values ("brilhante", "brilhante@gmail.com"); 

create table compras(
	id int not null primary key auto_increment unique,
    nome_fornecedor varchar(45),
    nome_produto varchar(45),
	quantidade int unsigned,
    preco_unitario decimal(10,2) unsigned,
    data_compra datetime default current_timestamp,
    valor_total decimal(10,2) unsigned,
    foreign key (nome_fornecedor) references fornecedores(nome),
    foreign key (nome_produto) references produtos(nome)
);

select * from compras;

insert into compras values (null, "brilhante", "arroz", 5, 5.5, default, 27.5);

create table vendas(
	id int not null auto_increment primary key,
    data_venda datetime default current_timestamp,
    valor_total decimal(10,2) unsigned,
    valor_pago decimal(10,2) unsigned,
    troco decimal(10,2),
    forma_pagamento varchar(45)
);

create table item_venda(
	id int not null auto_increment primary key,
    id_venda int,
    nome_produto varchar(45),
    quantidade int,
    preco_unitario decimal(10,2),
    subtotal decimal(10,2),
    foreign key (id_venda) references vendas(id),
    foreign key (nome_produto) references produtos(nome)
);

create table relatorios(
	id int not null auto_increment primary key,
    tipo_relatorio varchar(45),
    data_geracao datetime default current_timestamp,
    descricao varchar(200)
);