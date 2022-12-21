create table klaeff(
    Id serial primary key,
    githubid integer,
    username text,
    content varchar(300)
);
truncate table klaeff;

create table profil(
    Id integer primary key ,
    username text,
    avatar_url text
)