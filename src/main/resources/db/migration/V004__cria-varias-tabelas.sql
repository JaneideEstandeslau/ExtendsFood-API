create table endereco (
	id bigint not null auto_increment, 
    bairro varchar(20) not null, 
    cep varchar(10) not null, 
    complemento varchar(100), 
    numero varchar(20) not null, 
    rua varchar(100) not null, 
    endereco_cidade_id bigint, 
    primary key (id)
) engine=InnoDB default charset=utf8;

create table forma_pagamento (
	id bigint not null auto_increment, 
    data_atualizacao datetime not null, 
    descricao varchar(25) not null, 
    primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurante (
	id bigint not null auto_increment, 
    aberto bit, ativo bit, 
    cnpj varchar(18) not null, 
    nome varchar(100) not null,
    data_atualizacao datetime not null, 
    horario_fim_funcionamento varchar(5) not null, 
    horario_inicio_funcionamento varchar(5) not null, 
    taxa_frete decimal(19,2) not null, 
    telefone varchar(15) not null, 
    cozinha_id bigint not null, 
    endereco_id bigint, 
    primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurante_forma_pagamento (
	restaurante_id bigint not null, 
    forma_pagamento_id bigint not null, 
    primary key (restaurante_id, forma_pagamento_id)
) engine=InnoDB default charset=utf8;


alter table endereco add constraint fk_endereco_cidade foreign key (endereco_cidade_id) references cidade (id);

alter table restaurante add constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha (id);

alter table restaurante add constraint fk_restaurante_endereco foreign key (endereco_id) references endereco (id);

alter table restaurante_forma_pagamento add constraint fk_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table restaurante_forma_pagamento add constraint fk_restaurante foreign key (restaurante_id) references restaurante (id);
