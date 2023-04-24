truncate table h_user;
truncate table lookup;
insert into h_user (name, age, salary)
values('david',20, 100000);

insert into h_user (name, age, salary)
values('Bob',23, 30000);

insert into h_user (name, age, salary)
values('Mert',28, 12000);

insert into h_user (name, age, salary)
values('Oliver',35, 50000);

insert into h_user (name, age, salary)
values('George',32, 67000);

insert into h_user (name, age, salary)
values('Isaac',32, 67000);

insert into h_user (name, age, salary)
values('Thomas',29, 61000);

insert into h_user (name, age, salary)
values('Freddie',21, 78000);

insert into h_user (name, age, salary)
values('Freddie',36, 80000);

insert into h_user (name, age, salary)
values('James',39, 78000);

insert into h_user (name, age, salary)
values('James',41, 90000);

insert into lookup (id, type, name, value)
values(1, 'h_user_MESSAGE', 'h_user_CREATED', 'Successfully Created h_user');

insert into lookup (id, type, name, value)
values(2, 'h_user_MESSAGE', 'h_user_NOT_FOUND', 'Cannot Find h_user');

insert into lookup (id, type, name, value)
values(3, 'h_user_MESSAGE', 'h_user_DELETED', 'h_user is Deleted');
