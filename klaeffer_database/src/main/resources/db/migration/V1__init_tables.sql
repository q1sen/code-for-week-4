create table "user"
(
    id integer primary key
);

create table userinfo
(
    "user" integer primary key references "user"(id),
    image text
);

create table username
(
    userinfo integer primary key references userinfo ("user"),
    name text
);

create table klaeff
(
    id serial primary key,
    "user"  integer  references "user"(id)
);

create table content
(
    kleaff integer primary key references klaeff (id),
    content varchar(300)
);