insert into course(id, name, created_date, last_updated_date, is_deleted) 
values(10001, 'JPA in 50 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date, last_updated_date, is_deleted)
values(10002, 'JPA in 86 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date, last_updated_date, is_deleted)
values(10003, 'JPA in 100 Steps', sysdate(), sysdate(), false);

insert into passport(id, number)
values(40001, '1234');
insert into passport(id, number)
values(40002, '5678');
insert into passport(id, number)
values(40003, '90123');

insert into student(id, name, passport_id)
values(20001, 'John',40001);
insert into student(id, name, passport_id)
values(20002, 'Pieter',40002);
insert into student(id, name, passport_id)
values(20003, 'Jackson',40003);



insert into review(id, rating, description, course_id)
values(50001, 'FIVE', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values(50002, 'FOUR', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values(50003, 'THREE', 'Not bad', 10003);

insert into student_course(student_id, course_id)
values(20001,10001);
insert into student_course(student_id, course_id)
values(20002,10001);
insert into student_course(student_id, course_id)
values(20003,10001);
insert into student_course(student_id, course_id)
values(20001,10003);
