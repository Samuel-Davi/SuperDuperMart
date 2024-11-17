show databases;
create database superdupermart;
use superdupermart;
show tables;

insert into admin values(null, "samlima", "0907");

create table admin(
	id int not null auto_increment primary key,
    username varchar(20),
    password varchar(15)
);
select * from admin;

select * from produtos;

delete from produtos where nome = "frango empanado";

insert into produtos values ("arroz", 5.5, 7.15, 5, default);

create table produtos(
    nome varchar(45) not null unique primary key,
    preco_compra double not null,
    preco_venda double not null,
    estoque_atual int unsigned,
    data_cadastro datetime default current_timestamp
);
delete from fornecedores where nome = "perdigão";
select * from fornecedores;
insert into fornecedores values ("brilhante", "brilhante@gmail.com"); 

create table fornecedores(
    nome varchar(45) not null primary key unique,
    contato varchar(45)
);
select * from compras;

insert into compras values (null, "brilhante", "arroz", 5, 5.5, default, 27.5);

create table compras(
	id int not null primary key auto_increment unique,
    nome_fornecedor varchar(45),
    nome_produto varchar(45),
	quantidade int unsigned,
    preco_unitario double unsigned,
    data_compra datetime default current_timestamp,
    valor_total double unsigned,
    foreign key (nome_fornecedor) references fornecedores(nome),
    foreign key (nome_produto) references produtos(nome)
);

create table vendas(
	id int not null auto_increment primary key,
    nome_produto varchar(45),
    quantidade int unsigned,
    preco_unitario double unsigned,
    data_venda datetime default current_timestamp,
    valor_total double unsigned,
    forma_pagamento varchar(45),
    foreign key (nome_produto) references produtos(nome)
);

create table relatorios(
	id int not null auto_increment primary key,
    tipo_relatorio varchar(45),
    data_geracao datetime default current_timestamp,
    descricao varchar(200)
);