insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Indiana');

insert into restaurant (name, shipping_fee, kitchen_id) values ('Akidicasa', 10, 1);
insert into restaurant (name, shipping_fee, kitchen_id) values ('Boa vista', 2, 1);
insert into restaurant (name, shipping_fee, kitchen_id) values ('Varanda', 5, 2);

insert into state (name) values ('Minas Gerais');
insert into state (name) values ('São Paulo');
insert into state (name) values ('Paraná');

insert into city (name, state_id) values ('Itajubá', 1);
insert into city (name, state_id) values ('Pouso Alegre', 1);
insert into city (name, state_id) values ('Santa Rita', 1);
insert into city (name, state_id) values ('Aparecida', 2);
insert into city (name, state_id) values ('Lorena', 2);
insert into city (name, state_id) values ('Curitiba', 3);

insert into payment (description) values ('Cartão de credito');
insert into payment (description) values ('Dinheiro');

insert into permission (name, description) values ('Adicinoar cidade', 'Permite adicionar uma cidade no cadastro');
insert into permission (name, description) values ('Editar cidade', 'Permite editar um cadastro de cidade');
insert into permission (name, description) values ('Excluir cidade', 'Permite excluir uma cidade no cadastro');