alter table t_carts
    add constraint c_PK primary key (id);

alter table t_carts
    add constraint u_FK foreign key (user_email) references t_users (email);

alter table t_carts
    add constraint p_FK foreign key (product_name) references t_products (name);