create database agenda_contatos;

create table contatos ( id int auto_increment primary key, nome varchar(255), endereco varchar(255), email varchar(255), data_nascimento date);



insert into contatos (nome, endereco, email, data_nascimento) values ('A', 'Av. Teste A', 'exemplo_A@gmail.com', '2010-01-01');
insert into contatos (nome, endereco, email, data_nascimento) values ('B', 'Av. Teste B', 'exemplo_B@gmail.com', '2010-01-02');
insert into contatos (nome, endereco, email, data_nascimento) values ('C', 'Av. Teste C', 'exemplo_C@gmail.com', '2010-01-03');
insert into contatos (nome, endereco, email, data_nascimento) values ('D', 'Av. Teste D', 'exemplo_D@gmail.com', '2010-01-04');