drop table if exists employees;

create table employees (employee_id int not null, name varchar(255) not null, manager_id int not null, primary key (employee_id));

insert into employees (employee_id, name, manager_id) values (101, 'Karl', 100);
insert into employees (employee_id, name, manager_id) values (102, 'Ivo', 101);
insert into employees (employee_id, name, manager_id) values (105, 'Mehis', 101);
insert into employees (employee_id, name, manager_id) values (108, 'Aarne', 102);
insert into employees (employee_id, name, manager_id) values (110, 'Merle', 102);
insert into employees (employee_id, name, manager_id) values (111, 'Annika', 102);
insert into employees (employee_id, name, manager_id) values (116, 'Ants', 105);
insert into employees (employee_id, name, manager_id) values (120, 'Tanel', 101);
insert into employees (employee_id, name, manager_id) values (124, 'Sander', 108);