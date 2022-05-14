alter table t_users
    add constraint u_PK primary key (id);

alter table t_users
    add constraint ue_uk unique (email);