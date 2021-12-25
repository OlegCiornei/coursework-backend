create table t_products(
    id SERIAL not null,
    name varchar(100) not null,
    description varchar(255) not null,
    image varchar(500) not null,
    price decimal not null
);