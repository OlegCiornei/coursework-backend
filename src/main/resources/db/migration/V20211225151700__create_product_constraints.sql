alter table t_products
    add constraint p_PK primary key (id);

alter table t_products
    add constraint pn_uk unique (name);