BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	phone_number varchar(12),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE potholes (
pothole_id SERIAL,
address varchar(200),
description varchar(500),
severity int constraint threeDigits check (severity between 1 and 3),
reported_date date not null default current_date,
inspected_date date,
repair_date date,
can_contact boolean,
inspected boolean,
repaired boolean,
zipcode varchar(5),
in_traffic boolean,
potential_damage boolean,
location_details varchar(500),
reporter_id int,
CONSTRAINT PK_potholes PRIMARY KEY (pothole_id),
constraint FK_users_user_id foreign key (reporter_id) references users (user_id)

);
--CREATE TABLE user_potholes (
--pothole_id int,
--user_id int
--, constraint PK_user_potholes primary key (pothole_id, user_id)
--, constraint FK_users_user_id foreign key (user_id) references users (user_id)
--, constraint fk_potholes_pothole_id foreign key (pothole_id) references potholes (pothole_id)
--);


COMMIT TRANSACTION;
