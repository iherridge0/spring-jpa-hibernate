insert into course(id, name, created_date, last_updated_date) 
values(10001, 'JPA in 50 Steps', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10002, 'JPA in 86 Steps', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10003, 'JPA in 100 Steps', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) 
values(10004, 'Dummy 1', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10005, 'Dummy 2', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10006, 'Dummy 3', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) 
values(10007, 'Dummy 4', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10008, 'Dummy 5', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10009, 'Dummy 6', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) 
values(10010, 'Dummy 7', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10011, 'Dummy 8', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10012, 'Dummy 9', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) 
values(10013, 'Dummy 10', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10014, 'Dummy 11', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10015, 'Dummy 12', sysdate(), sysdate());

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
values(50001, '5', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values(50002, '4', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values(50003, '3', 'Not bad', 10003);

insert into student_course(student_id, course_id)
values(20001,10001);
insert into student_course(student_id, course_id)
values(20002,10001);
insert into student_course(student_id, course_id)
values(20003,10001);
insert into student_course(student_id, course_id)
values(20001,10003);
