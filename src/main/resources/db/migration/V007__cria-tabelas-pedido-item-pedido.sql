create table pedido (
	id bigint not null auto_increment,
    sub_total decimal(10,2) not null,
    taxa_frete decimal(10,2) not null,
    valor_total decimal(10,2) not null,
    data_criacao datetime,
    data_confirmacao datetime,
    data_entrega datetime,
    data_cancelamento datetime,
    status varchar(22) not null,    
    observacao varchar(100),
    
    restaurante_id bigint not null,
    forma_pagamento_id bigint not null,
    usuario_cliente_id bigint not null,
    endereco_id bigint not null,
    
	primary key (id),
     
    constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id),
    constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id),
    constraint fk_pedido_usuario foreign key (usuario_cliente_id) references usuario (id),
    constraint fk_pedido_endereco foreign key (endereco_id) references endereco (id)
)engine=InnoDB default charset=utf8;

create table item_pedido (
	id bigint not null auto_increment,
    preco_unitario decimal(10,2) not null,
    preco_total decimal(10,2) not null,
    quantidade smallint(6) not null,
    
    pedido_id bigint not null,
    produto_id bigint not null,
    
	primary key (id),
    
    constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id),
    constraint fk_item_pedido_produto foreign key (produto_id) references produto (id)
)engine=InnoDB default charset=utf8;

create table item_pedido_item_complemento (
	item_pedido_id bigint not null, 
	item_complemento_id bigint not null, 
	primary key (item_pedido_id, item_complemento_id)
) engine=InnoDB default charset=utf8;

alter table item_pedido_item_complemento add constraint fk_item_pedido_item_complemento_pedido
foreign key (item_pedido_id) references item_pedido (id);

alter table item_pedido_item_complemento add constraint fk_item_pedido_item_complemento_complemento
foreign key (item_complemento_id) references item_complemento (id);






    