BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO potholes (description, address, severity, inspected_date, repair_date, can_contact, reported_date, inspected, repaired, zipcode, in_traffic, potential_damage, location_details)
VALUES ('Large pothole on Main Street', 'Main Street, Cityville', 3, '2008-11-11', '2008-11-11', 'true','2008-11-11','true', 'true', 44444, 'true', 'true', 'seven eleven yeah');
--
--INSERT INTO potholes (description, location, severity)
--VALUES ('Deep pothole near intersection', 'First Avenue and Elm Street, Townsville', 2);
--
--INSERT INTO potholes  (description, location, severity)
--VALUES ('Multiple small potholes on Side Road', 'Side Road, Villagetown', 1);

COMMIT TRANSACTION;
