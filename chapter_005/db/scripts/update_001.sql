create table if not exists vacancy (
  id serial primary key,
  name varchar(2000),
  text varchar(4000),
  link varchar(1000)
);
