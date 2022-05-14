CREATE TABLE t_users
(
    id       SERIAL not null,
    email    varchar(255) default NULL,
    password TEXT         default NULL,
    role     varchar(20)  default 'USER',
    status   varchar(20)  default 'ACTIVE'
);