alter table t_carts
    add constraint u_FK foreign key (user_email) references t_users (email);

alter table t_carts
    add constraint p_FK foreign key (product_name) references t_products (name);