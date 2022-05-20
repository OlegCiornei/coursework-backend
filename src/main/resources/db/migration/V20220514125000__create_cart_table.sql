create table t_carts
(
    id           SERIAL primary key,
    user_email   varchar(255) not null,
    product_name varchar(100) not null,
    amount       INTEGER      not null
)