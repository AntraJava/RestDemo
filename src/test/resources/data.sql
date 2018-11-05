truncate table user;
truncate table lookup;

insert into user (id, name, age, salary)
values(1, 'david',20, 100000);


insert into lookup (id, type, name, value)
values(1, 'USER_MESSAGE', 'USER_CREATED', 'Successfully Created User');

insert into lookup (id, type, name, value)
values(2, 'USER_MESSAGE', 'USER_NOT_FOUND', 'Cannot Find User');


insert into lookup (id, type, name, value)
values(3, 'USER_MESSAGE', 'USER_DELETED', 'User is Deleted');