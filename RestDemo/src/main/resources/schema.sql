create table IF NOT EXISTS user
(
   id integer not null auto_increment,
   name varchar(255) not null,
   age integer not null,
   salary integer not null,
   primary key(id)
);