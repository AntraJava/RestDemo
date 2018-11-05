truncate table user;
truncate table lookup;
insert into user (name, age, salary)
values('david',20, 100000);

insert into user (name, age, salary)
values('Bob',23, 30000);

insert into user (name, age, salary)
values('Mert',28, 12000);

insert into user (name, age, salary)
values('Oliver',35, 50000);

insert into user (name, age, salary)
values('George',32, 67000);

insert into user (name, age, salary)
values('Isaac',32, 67000);

insert into user (name, age, salary)
values('Thomas',29, 61000);

insert into user (name, age, salary)
values('Freddie',21, 78000);

insert into user (name, age, salary)
values('Freddie',36, 80000);

insert into user (name, age, salary)
values('James',39, 78000);

insert into user (name, age, salary)
values('James',41, 90000);

insert into lookup (id, type, name, value)
values(1, 'USER_MESSAGE', 'USER_CREATED', 'Successfully Created User');

insert into lookup (id, type, name, value)
values(2, 'USER_MESSAGE', 'USER_NOT_FOUND', 'Cannot Find User');

insert into lookup (id, type, name, value)
values(3, 'USER_MESSAGE', 'USER_DELETED', 'User is Deleted');