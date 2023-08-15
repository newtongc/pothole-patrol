BEGIN TRANSACTION;

DROP TABLE IF EXISTS potholes;
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
    img_url varchar(500),
    CONSTRAINT PK_potholes PRIMARY KEY (pothole_id),
    CONSTRAINT FK_users_user_id FOREIGN KEY (reporter_id) REFERENCES users (user_id),
    CONSTRAINT check_inspected_date CHECK ((inspected = false) OR (inspected = true AND inspected_date IS NOT NULL)),
    CONSTRAINT check_repair_date CHECK ((repaired = false) OR (repaired = true AND repair_date IS NOT NULL)),
    CONSTRAINT check_repair_inspection_dates CHECK (
        (inspected = false OR (inspected = true AND inspected_date IS NOT NULL AND (repaired = false OR (repaired = true AND repair_date IS NOT NULL))))
        AND (repaired = false OR (repaired = true AND (repair_date IS NULL OR repair_date > inspected_date)))
    )
);

COMMIT TRANSACTION;








