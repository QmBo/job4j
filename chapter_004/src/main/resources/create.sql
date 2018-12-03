create table rules(
	id serial primary key,
	name varchar(2000)
	);
create table role(
	id serial primary key,
	name varchar(2000)
	);
create table rules_role(
	id serial primary key,
	name varchar(2000),
	role_id int references role(id),
	rules_id int references rules(id)
	);
create table category(
	id serial primary key,
	name varchar(2000)
	);
create table state(
	id serial primary key,
	name varchar(2000)
	);
create table users(
	id serial primary key,
	name varchar(2000),
	login varchar(2000),
	password varchar(2000),
	role_id int references role(id)
	);
create table item(
	id serial primary key,
	name varchar(2000),
	description varchar(2000),
	create_time bigint,
	category_id int references category(id),
	state_id int references state(id),
	user_id int references users(id)
	);
create table attach(
	id serial primary key,
	name varchar(2000),
	description varchar(2000),
	file_address varchar(2000),
	item_id int references item(id)
	);
create table comment(
	id serial primary key,
	name varchar(2000),
	item_id int references item(id),
	user_id int references users(id)
	);
insert into role (name)
	values ('admin'), ('root'), ('reader'), ('creator');
insert into rules (name)
	values ('read'), ('write');
insert into users (name, login, password, role_id)
	values ('Admin', 'admin', 'rooter', (select id from role where name ='admin'));
insert  into rules_role (name, role_id, rules_id)
	values
		('can read', (select id from role where name='admin'),(select id from rules where name ='read')),
		('can write', (select id from role where name='admin'), (select id from rules where name ='write'));
insert into category (name)
	values
		('task'), ('report');
insert into state (name)
	values
		 ('new'), ('open'), ('closed');
insert into item (name, description, create_time)
	values
		('Test', 'This is test description.', 1514783471000);
insert into attach (name, description, file_address, item_id)
	values
		('test.png', 'This is test description for file.', '//test.png', (select id from item where name = 'Test'));
insert into comment (name, item_id, user_id)
	values
		('This is test comment.', (select id from item where name = 'Test'), (select id from users where name = 'Admin'));