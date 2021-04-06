create table pedido (
	id bigint not null auto_increment,
    sub_total decimal(10,2) not null,
    taxa_frete decimal(10,2) not null,
    valor_total decimal(10,2) not null,
    data_criacao datetime not null,
    data_confirmacao datetime not null,
    data_entrega datetime not null,
    data_cancelamento datetime not null,
    status varchar(22) not null,
    
    restaurante_id bigint not null,
    forma_pagamento_id bigint not null,
    usuario_cliente_id bigint not null,
    
	primary key (id),
     
    constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id),
    constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id),
    constraint fk_pedido_usuario foreign key (usuario_cliente_id) references usuario (id)
)engine=InnoDB default charset=utf8;

create table item_pedido (
	id bigint not null auto_increment,
    preco_unitario decimal(10,2) not null,
    preco_total decimal(10,2) not null,
    quantidade smallint(6) not null,
    observacao varchar(100) not null,
    
    pedido_id bigint not null,
    produto_id bigint not null,
    
	primary key (id),
    
    constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id),
    constraint fk_item_pedido_produto foreign key (produto_id) references produto (id)
)engine=InnoDB default charset=utf8;
    