BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE potholes (
pothole_id SERIAL,
location varchar(200),
description varchar(500),
severity int constraint threeDigits check (severity between 1 and 3),
reported_date date not null default current_date,
CONSTRAINT PK_potholes PRIMARY KEY (pothole_id)
);
CREATE TABLE user_potholes (
pothole_id int,
user_id int
, constraint PK_user_potholes primary key (pothole_id, user_id)
, constraint FK_users_user_id foreign key (user_id) references users (user_id)
, constraint fk_potholes_pothole_id foreign key (pothole_id) references potholes (pothole_id)
);


COMMIT TRANSACTION;
