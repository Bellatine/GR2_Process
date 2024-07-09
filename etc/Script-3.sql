drop table `user`  ;
create table user(
	 id int PRIMARY key AUTO_INCREMENT,
	 username varchar(100) not null,
	 fullname varchar(100) not null,
	 email varchar(100) not null,
	 password varchar(100) not null,
	 role int not null
);

create table categories(
	id int primary key AUTO_INCREMENT,
	user_id int not null,
	name varchar(100) not null,
	description varchar(100) not null,
	color varchar(100) not null,
	foreign key (user_id) references user(id)
);

create table transactions(
	id int primary key auto_increment ,
	category_id int not null,
	user_id int not null,
	amount int not null,
	type int not null,
	trans_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	note varchar(500),
	status int not null,
	shop_id int not null,
	foreign key (user_id) references user(id),
	foreign key (category_id) references categories(id)
);


create table money_jar(
	id int primary key AUTO_INCREMENT,
	category_id int not null,
	user_id int not null,
	name varchar(100) not null,
	amount_max int not null,
	balance int not null,
	description varchar(500) not null,
	foreign key (user_id) references user(id),
	foreign key (category_id) references categories(id)
);

create table saving(
	id int primary key AUTO_INCREMENT,
	user_id int not null,
	type int not null,
	code varchar(100) not null,
	date_from timestamp,
	date_to timestamp,
	amount int not null,
	interest_rates int not null,
	description varchar(500) not null,
	foreign key (user_id) references user(id)
);

create table loan(
	id int primary key AUTO_INCREMENT,
	user_id int not null,
	type int not null,
	name varchar(100) not null,
	date_from timestamp,
	date_to timestamp,
	amount int not null,
	interest_rates int not null,
	description varchar(500) not null,
	foreign key (user_id) references user(id)
);

select * from `user` u ;