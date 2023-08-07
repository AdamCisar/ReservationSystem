create sequence privilege_seq start with 1 increment by 50;
create sequence reservation_seq start with 1 increment by 50;
create sequence role_seq start with 1 increment by 50;
create sequence users_seq start with 1 increment by 50;

    create table privilege (
       id bigint not null,
        name varchar(255),
        primary key (id)
    );

    create table reservation (
       id bigint not null,
        reservation_date date,
        reservation_time time,
        user_id bigint,
        primary key (id)
    );

    create table role (
       id bigint not null,
        name varchar(255),
        primary key (id)
    );

    create table roles_privileges (
       role_id bigint not null,
        privilege_id bigint not null
    );

    create table users (
       id bigint not null,
        email varchar(255),
        enabled boolean not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    );

    create table users_roles (
       user_id bigint not null,
        role_id bigint not null
    );

    alter table if exists reservation 
       add constraint FKrea93581tgkq61mdl13hehami 
       foreign key (user_id) 
       references users;

    alter table if exists roles_privileges 
       add constraint FK5yjwxw2gvfyu76j3rgqwo685u 
       foreign key (privilege_id) 
       references privilege;

    alter table if exists roles_privileges 
       add constraint FK9h2vewsqh8luhfq71xokh4who 
       foreign key (role_id) 
       references role;

    alter table if exists users_roles 
       add constraint FKt4v0rrweyk393bdgt107vdx0x 
       foreign key (role_id) 
       references role;

    alter table if exists users_roles 
       add constraint FK2o0jvgh89lemvvo17cbqvdxaa 
       foreign key (user_id) 
       references users;
