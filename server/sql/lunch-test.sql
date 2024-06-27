drop database if exists lunch_test;
create database lunch_test;
use lunch_test;

create table app_role(
    role_id int primary key auto_increment,
    role_name varchar(50) not null
);

create table app_user(
    user_id int primary key auto_increment,
    user_first_name varchar(50) not null,
    user_last_name varchar(50) not null,
    user_username varchar(50) not null,
    user_email varchar(250) not null,
    user_hashed_password varchar(2048) not null,
    user_is_banned boolean default false
);

create table category(
    category_id int primary key auto_increment,
    category_name varchar(100) not null
);

create table item(
    item_id int primary key auto_increment,
    item_title varchar(50) not null,
    item_description varchar(2048) not null,
    item_price decimal(10, 2) not null,
    item_photo varchar(250),
    item_disabled boolean default false,
    category_id int not null,

    constraint fk_item_category_id
    foreign key(category_id)
    references category(category_id)
);

create table modifiers(
    modifier_id int primary auto_increment,
    modifier_name varchar(50) not null,
    modifier_price decimal(10, 2) not null,
    modifier_disabled boolean default false,
    item_id int not null,

    constraint fk_modifers_item_id
    foreign key(item_id)
    references item(item_id)
    
);

create table user_role (
	user_id int not null,
    role_id int not null,
    
    constraint pk_user_role
		primary key (user_id, role_id),
	constraint fk_user_role_user_id
		foreign key (user_id)
		references app_user(user_id),
	constraint fk_user_role_role_id
		foreign key (role_id)
        references app_role(role_id)
);


delimiter //
create procedure set_known_good_state
begin

    delete from app_role;
    alter table app_role auto_increment = 1;
    delete from app_user;
    alter table app_user auto_increment = 1;
    delete from category;
    alter table category auto_increment = 1;
    delete from item;
    alter table item auto_increment = 1;
    delete from modifiers;
    alter table modifiers auto_increment = 1;
    delete from user_role;

    insert into app_role(role_name) values(
        ('ROLE_USER'),
        ('ROLE_ADMIN')
    );

    insert into app_user(user_first_name, user_last_name, user_username, user_email, user_hashed_password) values(
        ('Tony', 'Greekman', 'MrTony', 'Tony@nbl.com', 'password'),
         ('Tom', 'Coffey', 'TomTom', 'Tomtom@nbl.com', 'password')
    );

    insert into category(category_name) values(
        ('Hot Dogs, Burgers, French Fries'),
        ('Breakfast Platter'),
        ('Breakfast Sandwiches'),
        ('Beverages'),
        ('Jars of Sauce')
    );

    insert into item(item_title, item_description, item_price, item_photo, category_id) values(
        ('Texas Hot Weiner', 'description', 2.75, 'temp.jpg', 1),
        ('Cheeseburger', 'description', 3.25, 'temp.jpg', 1),
        ('French Fries', 'french fries description', 2.50, 'temp.jpg', 1),
        ('Breakfast Platter', 'eggscription', 7.50, 'temp.jpg', 2),
        ('Egg Sandwich', 'description', 4.50, 'temp.jpg', 3),
        ('Coffee', 'Hot Coffee', 1.50, 'temp.jpg', 4),
        ('Pepsi', 'Can', 1.75, 'soda.jpg', 4),
        ('Small Jar', '13 ounces', 9.00, 'temp.jpg', 5),
        ('Buffalo Chicken Bacon Ranch Fries', 'fire', 5.00, 'temp.jpg', 5)
    );

    insert into modifiers(modifier_name, modifier_price, item_id) values(
        ('Extra Cheese', 1.00, 2),
        ('Cheese', 1.00, 1),
        ('Bacon', 1.00, 1),
        ('Bacon', 1.50, 2),
        ('Bacon', 1.00, 3),
        ('Bacon', 2.25, 4),
        ('Bacon', 2.25, 5),
        ('Extra Bacon',2.00, 9),
        ('Cheese', 2.00, 9),
        ('Sauce', 0.00, 1),
        ('Sauce', 0.00, 2),
        ('Sauce', 0.50, 3),
        ('Sauce', 0.50, 4),
   
    );
      





end //
delimiter ;







