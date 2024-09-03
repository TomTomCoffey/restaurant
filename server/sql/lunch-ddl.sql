drop database if exists lunch;
create database lunch;
use lunch;

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

create table category_modifiers(
    category_modifiers_id int primary key auto_increment,
    category_modifiers_title varchar(50) not null,
    category_modifiers_required boolean not null
);

create table modifiers(
    modifier_id int primary key auto_increment,
    modifier_name varchar(50) not null,
    modifier_price decimal(10, 2) not null,
    modifier_disabled boolean default false,
    category_modifiers_id int not null,
    constraint fk_modifiers_category_modifiers_id
    foreign key(category_modifiers_id)
    references category_modifiers(category_modifiers_id)
  
);



create table submodifier(
    submodifier_id int primary key auto_increment,
    modifier_id int not null,
    item_id int not null,
    constraint fk_submodifier_modifier_id
    foreign key(modifier_id)
    references modifiers(modifier_id),
    constraint fk_submodifier_item_id
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
