set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from endereco;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;
delete from categoria_produto;
delete from restaurante_usuario_responsavel;
delete from complemento;
delete from item_complemento;
delete from pedido;
delete from item_pedido;
delete from item_pedido_item_complemento;
delete from dia_semana;
delete from produto_dia_semana;
delete from foto_produto;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table endereco auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;
alter table categoria_produto auto_increment = 1;
alter table produto auto_increment = 1;
alter table complemento auto_increment = 1;
alter table item_complemento auto_increment = 1;
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;
alter table dia_semana auto_increment = 1;
alter table produto_dia_semana auto_increment = 1;

insert into dia_semana (id, dia_semana) values (1, 'DOMINGO');
insert into dia_semana (id, dia_semana) values (2, 'SEGUNDA');
insert into dia_semana (id, dia_semana) values (3, 'TERCA');
insert into dia_semana (id, dia_semana) values (4, 'QUARTA');
insert into dia_semana (id, dia_semana) values (5, 'QUINTA');
insert into dia_semana (id, dia_semana) values (6, 'SEXTA');
insert into dia_semana (id, dia_semana) values (7, 'SABADO');


insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into endereco (id, cep, rua, numero, complemento, bairro, endereco_cidade_id) values (1, "58.140.695", "Barão da Passagem", "19", "APTO 01", "Catolé", 1);

insert into restaurante (id, aberto, ativo, nome, cnpj, data_atualizacao, horario_inicio_funcionamento, horario_fim_funcionamento, taxa_frete, telefone, cozinha_id, endereco_id)
values (1, 0, 1, "Ki Pastel", "05.890.872/0001-96", current_timestamp, "18:00", "01:00", "10.00", "(83) 99986-8985", 1, 1);
insert into restaurante (id, aberto, ativo, nome, cnpj, data_atualizacao, horario_inicio_funcionamento, horario_fim_funcionamento, taxa_frete, telefone, cozinha_id, endereco_id)
values (2, 0, 1, "Chafaris", "23.109.212/0001-07", current_timestamp, "18:00", "01:00", "10.00", "(83) 99986-8985", 1, 1);
insert into restaurante (id, aberto, ativo, nome, cnpj, data_atualizacao, horario_inicio_funcionamento, horario_fim_funcionamento, taxa_frete, telefone, cozinha_id, endereco_id)
values (3, 0, 1, "Rei da Pizza", "75.176.664/0001-05", current_timestamp, "18:00", "01:00", "10.00", "(83) 99986-8985", 1, 1);
insert into restaurante (id, aberto, ativo, nome, cnpj, data_atualizacao, horario_inicio_funcionamento, horario_fim_funcionamento, taxa_frete, telefone, cozinha_id, endereco_id)
values (4, 0, 1, "Kaio massas", "03.386.538/0001-74", current_timestamp, "18:00", "01:00", "10.00", "(83) 99986-8985", 1, 1);

insert into forma_pagamento (id, descricao, data_atualizacao) values (1, 'Cartão de crédito', current_timestamp);
insert into forma_pagamento (id, descricao, data_atualizacao) values (2, 'Cartão de débito', current_timestamp);
insert into forma_pagamento (id, descricao, data_atualizacao) values (3, 'Dinheiro', current_timestamp);

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao) values (3, 'CONSULTAR_FORMAS_PAGAMENTO', 'Permite consultar formas de pagamento');
insert into permissao (id, nome, descricao) values (4, 'EDITAR_FORMAS_PAGAMENTO', 'Permite criar ou editar formas de pagamento');
insert into permissao (id, nome, descricao) values (5, 'CONSULTAR_CIDADES', 'Permite consultar cidades');
insert into permissao (id, nome, descricao) values (6, 'EDITAR_CIDADES', 'Permite criar ou editar cidades');
insert into permissao (id, nome, descricao) values (7, 'CONSULTAR_ESTADOS', 'Permite consultar estados');
insert into permissao (id, nome, descricao) values (8, 'EDITAR_ESTADOS', 'Permite criar ou editar estados');
insert into permissao (id, nome, descricao) values (9, 'CONSULTAR_USUARIOS', 'Permite consultar usuários');
insert into permissao (id, nome, descricao) values (10, 'EDITAR_USUARIOS', 'Permite criar ou editar usuários');
insert into permissao (id, nome, descricao) values (11, 'CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes');
insert into permissao (id, nome, descricao) values (12, 'EDITAR_RESTAURANTES', 'Permite criar, editar ou gerenciar restaurantes');
insert into permissao (id, nome, descricao) values (13, 'CONSULTAR_PRODUTOS', 'Permite consultar produtos');
insert into permissao (id, nome, descricao) values (14, 'EDITAR_PRODUTOS', 'Permite criar ou editar produtos');
insert into permissao (id, nome, descricao) values (15, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into permissao (id, nome, descricao) values (16, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into permissao (id, nome, descricao) values (17, 'GERAR_RELATORIOS', 'Permite gerar relatórios');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2);

insert into categoria_produto (id, descricao, restaurante_id) values (1, 'Pastel Salgado', 1);
insert into categoria_produto (id, descricao, restaurante_id) values (2, 'Pastel Doce', 1);

insert into categoria_produto (id, descricao, restaurante_id) values (3, 'Hamburguer', 2);
insert into categoria_produto (id, descricao, restaurante_id) values (4, 'Cachrro-quente', 2);

insert into categoria_produto (id, descricao, restaurante_id) values (5, 'Pizza Salgada', 3);
insert into categoria_produto (id, descricao, restaurante_id) values (6, 'Pizza Doce', 3);

insert into categoria_produto (id, descricao, restaurante_id) values (7, 'Macarrão', 4);
insert into categoria_produto (id, descricao, restaurante_id) values (8, 'Lasanha', 4);

insert into produto (id, nome, descricao, disponivel, preco, categoria_produto_id) values (1, 'pastel', 'Delicioso pastel de carne', 1, 78.90, 1);
insert into produto (id, nome, descricao, disponivel, preco, categoria_produto_id) values (2, 'pastel', 'Delicioso pastel de romel e julieta', 1, 110, 2);

insert into produto (id, nome, descricao, disponivel, preco, categoria_produto_id) values (3, 'Hamburguer', 'Hamburguer vegetariano', 1, 87.20, 3);
insert into produto (id, nome, descricao, disponivel, preco, categoria_produto_id) values (4, 'cachrro quente', 'Cachorro-quente com molho de frando', 1, 87.20, 4);

insert into produto (id, nome, descricao, disponivel, preco, categoria_produto_id) values (5, 'Pizza', 'Calabresa mineira', 1, 21, 5);
insert into produto (id, nome, descricao, disponivel, preco, categoria_produto_id) values (6, 'Pizza', 'Ovomaltine', 1, 43, 6);

insert into produto (id, nome, descricao, disponivel, preco, categoria_produto_id) values (7, 'Massa', 'Macrrão ao molho branco', 1, 79, 7);
insert into produto (id, nome, descricao, disponivel, preco, categoria_produto_id) values (8, 'Massa', 'Lasanha a bolonhesa', 1, 89, 8);


insert into produto_dia_semana (produto_id, dia_disponivel_id) values (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6);


insert into complemento (id, descricao, qtd_minima, qtd_maxima, obrigatorio, produto_id) values (1, 'Carne', 0, 3, true, 1);
insert into complemento (id, descricao, qtd_minima, qtd_maxima, obrigatorio, produto_id) values (2, 'Queijo', 0, 3, true, 1);

insert into item_complemento (id, nome, preco, disponivel, complemento_id) values (1, 'Crane de Sol', 30.00, true, 1);
insert into item_complemento (id, nome, preco, disponivel, complemento_id) values (2, 'Crane moida', 30.00, true, 1);

insert into item_complemento (id, nome, preco, disponivel, complemento_id) values (3, 'Queijo Coalho', 30.00, true, 2);
insert into item_complemento (id, nome, preco, disponivel, complemento_id) values (4, 'Queijo Mussarela', 30.00, true, 2);


insert into grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');


# Adiciona todas as permissoes no grupo do gerente
insert into grupo_permissao (grupo_id, permissao_id)
select 1, id from permissao;

# Adiciona permissoes no grupo do vendedor
insert into grupo_permissao (grupo_id, permissao_id)
select 2, id from permissao where nome like 'CONSULTAR_%';

insert into grupo_permissao (grupo_id, permissao_id) values (2, 14);

# Adiciona permissoes no grupo do auxiliar
insert into grupo_permissao (grupo_id, permissao_id)
select 3, id from permissao where nome like 'CONSULTAR_%';

# Adiciona permissoes no grupo cadastrador
insert into grupo_permissao (grupo_id, permissao_id)
select 4, id from permissao where nome like '%_RESTAURANTES' or nome like '%_PRODUTOS';


insert into usuario (id, nome, email, senha, cpf, data_cadastro) values
(1, 'João da Silva', 'janeide.estan@gmail.com', '$2a$12$VZNPVTgGmZY1wiZCyc7qiug4ixiITDTq4etDabL1Nto1.nTEW4BjK', '481.051.910-40', current_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '$2a$12$VZNPVTgGmZY1wiZCyc7qiug4ixiITDTq4etDabL1Nto1.nTEW4BjK', '825.920.850-41', current_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '$2a$12$VZNPVTgGmZY1wiZCyc7qiug4ixiITDTq4etDabL1Nto1.nTEW4BjK', '440.483.740-28', current_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '$2a$12$VZNPVTgGmZY1wiZCyc7qiug4ixiITDTq4etDabL1Nto1.nTEW4BjK', '642.165.460-64', current_timestamp);
 
insert into usuario (id, nome, email, senha, cpf, data_cadastro) values
(5, 'Manoel Lima', 'manoel.loja@gmail.com', '$2a$12$VZNPVTgGmZY1wiZCyc7qiug4ixiITDTq4etDabL1Nto1.nTEW4BjK', '013.551.700-10', current_timestamp);

insert into endereco (id, cep, rua, numero, bairro, endereco_cidade_id, endereco_usuario_id, ativo_usuario) values (2, "58.140.695", "Barão do Abiaí", "190", "Centro", 1, 1, true);
insert into endereco (id, cep, rua, numero, bairro, endereco_cidade_id, endereco_usuario_id, ativo_usuario) values (3, "58.140.695", "AV Elpidio de Almeida", "210", "Sandra Cavalcante", 1, 2, true);
insert into endereco (id, cep, rua, numero, bairro, endereco_cidade_id, endereco_usuario_id, ativo_usuario) values (4, "58.140.695", "Vigario Calixto", "1002", "Centro", 1,3, true);

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 5), (3, 5);

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_id,
    status, data_criacao, sub_total, taxa_frete, valor_total)
values (1, 1, 1, 1, 1, 'AGUARDANDO_CONFIRMACAO', current_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total)
values (1, 1, 1, 1, 78.9, 78.9);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total)
values (2, 1, 2, 2, 110, 220);


insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_id, 
	status, data_criacao, sub_total, taxa_frete, valor_total)
values (2, 4, 1, 2, 1, 'AGUARDANDO_CONFIRMACAO', current_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total)
values (3, 2, 6, 1, 79, 79);

insert into item_pedido_item_complemento (item_pedido_id, item_complemento_id) values (1, 1), (1, 2), (2, 2);











