create table t_products
(
    id          SERIAL primary key,
    name        varchar(100) not null,
    description varchar(500) not null,
    image       varchar(500) not null,
    price       decimal      not null,
    category    varchar(50)  not null,
    gender      varchar(20)  not null,
    agecategory varchar(20)  not null
);