insert into course(id, name, created_date, last_updated_date) 
values(10001, 'JPA in 50 Steps', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10002, 'JPA in 86 Steps', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10003, 'JPA in 100 Steps', sysdate(), sysdate());

insert into passport(id, number)
values(40001, '1234');
insert into passport(id, number)
values(40002, '5678');
insert into passport(id, number)
values(40003, '9012');

insert into student(id, name, passport_id)
values(20001, 'John',40001);
insert into student(id, name, passport_id)
values(20002, 'Pieter',40002);
insert into student(id, name, passport_id)
values(20003, 'Jackson',40003);



insert into review(id, rating, description, course_id)
values(50001, '5', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values(50002, '4', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values(50003, '3', 'Not bad', 10003);
