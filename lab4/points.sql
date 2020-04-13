connect 'jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine';
--drop table points;
create table points(name varchar(20), coord_x integer, coord_y integer, coord_z integer,m integer);
-- вставка тестових даних
insert into points values ('A', -5, 0, 0,1);
insert into points values ('B', 0, 1, 10, 37);
insert into points values ('C', 10, -150, 3, 73);
insert into points values ('Not A', 5, 0, 0, 4815162324);
-- вибрати все із таблиці для перевірки
select * from points; 
-- відключення і вихід
disconnect;
exit;
