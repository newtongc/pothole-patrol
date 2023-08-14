BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role, phone_number) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', '098-765-4321');
INSERT INTO users (username,password_hash,role, phone_number) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', '123-456-7890');

INSERT INTO potholes (address, zipcode, severity, repair_date, Inspected_date, can_contact, inspected, repaired, in_traffic, potential_damage, description, location_details, reporter_id)
VALUES ('123 Main St', '12345', 2, '2023-08-14', '2023-08-10', true, true, false, true, true, 'Large pothole on the road.', 'Near the intersection.', 1);

INSERT INTO potholes (address, zipcode, severity, repair_date, Inspected_date, can_contact, inspected, repaired, in_traffic, potential_damage, description, location_details, reporter_id)
VALUES ('456 Elm Ave', '67890', 1, NULL, '2023-08-12', false, false, false, false, true, 'Small pothole on the sidewalk.', 'In front of the park.', 2);

INSERT INTO potholes (address, zipcode, severity, repair_date, Inspected_date, can_contact, inspected, repaired, in_traffic, potential_damage, description, location_details, reporter_id)
VALUES ('789 Oak Rd', '54321', 3, NULL, NULL, true, false, false, false, false, 'Huge pothole causing traffic issues.', 'Busy intersection downtown.', 1);

INSERT INTO potholes (address, zipcode, severity, repair_date, Inspected_date, can_contact, inspected, repaired, in_traffic, potential_damage, description, location_details, reporter_id)
VALUES ('101 Pine Ln', '98765', 2, NULL, NULL, true, false, false, true, true, 'Pothole with potential car damage.', 'Residential area.', 2);

INSERT INTO potholes (address, zipcode, severity, repair_date, Inspected_date, can_contact, inspected, repaired, in_traffic, potential_damage, description, location_details, reporter_id)
VALUES ('222 Maple Dr', '13579', 1, '2023-08-20', '2023-08-18', true, true, true, true, false, 'Pothole recently repaired.', 'Near the school.', 2);

COMMIT TRANSACTION;
